package by.vorakh.alex.spring_company.service;

import by.vorakh.alex.spring_company.client.ClientException;
import by.vorakh.alex.spring_company.client.RandomEntityClient;
import by.vorakh.alex.spring_company.converter.CompanyToCompanyViewModelConverter;
import by.vorakh.alex.spring_company.converter.EmployeeToEmployeeViewModelConverter;
import by.vorakh.alex.spring_company.model.external.ExternalEmployee;
import by.vorakh.alex.spring_company.model.payload.CompanyPayload;
import by.vorakh.alex.spring_company.model.view_model.CompanyViewModel;
import by.vorakh.alex.spring_company.model.view_model.EmployeeViewModel;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.repository.CompanyDAO;
import by.vorakh.alex.spring_company.repository.entity.Company;
import by.vorakh.alex.spring_company.repository.entity.Employee;
import rx.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CompanyService implements 
        ServiceInterface<CompanyViewModel, CompanyPayload> {
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CompanyToCompanyViewModelConverter convertor;
    @Autowired
    private EmployeeToEmployeeViewModelConverter employeeConvertor;
    @Autowired
    private RandomEntityClient companyClient;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public List<CompanyViewModel> getAll() {
        List<CompanyViewModel> companyViewModelList = new ArrayList<>();
        
        companyDAO.getAll().forEach(company ->
            companyViewModelList.add(convertor.convert(company)));

        return companyViewModelList;
    }
    
    @Override
    public CompanyViewModel getById(int id) {
        return convertor.convert(companyDAO.getById(id));
    }

    @Override
    @Transactional
    public IdViewModel create(CompanyPayload newPayload) {
        String name = newPayload.getName();
        if (companyDAO.isContained(name)) {
            throw new ServiceException("The \"" + name + 
        	    "\" company cannot be created, because to exist in database.");
        }
	
      	Set<Employee> employeeList = employeeService
                .findListByIDs(newPayload.getEmployeeIdList());
      	
      	Company newCompany =  new Company(name, employeeList);
      	
      	try {
      	    int createdID = companyDAO.create(newCompany);
      	    return new IdViewModel(createdID);
      	} catch (EntityExistsException e) {
      	    throw new ServiceException("The \"" + newCompany  
      		          + "\" cannot be created, because to exist in database.", e);
      	} catch (IllegalArgumentException ex) {
      	    throw new ServiceException("The COMPANY cannot be created, because " 
                    + newCompany +  " is not a COMPANY object.", ex);
      	} catch (javax.persistence.TransactionRequiredException exc) { 
      	    throw new ServiceException("The \"" + newCompany 
                    + "\" cannot be created, NO Transaction.", exc);
      	} catch (javax.persistence.PersistenceException ex1) { 
      	    throw new ServiceException("The \"" + newCompany 
                    + "\" cannot be created, the database is not updated.", ex1);
      	}	
    }

    @Override
    @Transactional
    public void update(CompanyPayload editedPayload) {
        String name = editedPayload.getName();
        
        int id = editedPayload.getId();
        
        if (companyDAO.isContained(name)) {
            throw new ServiceException("The \"" + name + "\" company cannot " 
                    + "be updated, because to exist in database.");
        }
        
        Company editedCompany = companyDAO.getById(id);
        
        if (editedCompany == null) {
            throw new ServiceException("The Company cannot be updated, because " 
                    + "the Company with \'"+ id 
                    +"\' ID does not exist in database.");
        }
        
        Set<Employee> employeeList = employeeService
                .findListByIDs(editedPayload.getEmployeeIdList());
                
        editedCompany.setName(name).setEmployees(employeeList);
        
        update(editedCompany);
    }
    
    @Transactional
    public void update(Company editedCompany) {
      	try {
      	    companyDAO.update(editedCompany);
      	} catch (IllegalArgumentException ex) {
      	    throw new ServiceException("The Company cannot be updated, because " 
                    +   editedCompany +  " is not a Company object.", ex);
      	} catch (javax.persistence.TransactionRequiredException exc) { 
      	    throw new ServiceException("The \"" + editedCompany 
                    + "\" cannot be updated, NO Transaction.", exc);
      	}
    }
    
    @Override
    @Transactional
    public void delete(int id) {
        Company deletedCompany = companyDAO.getById(id);
        if (deletedCompany == null) {
            throw new ServiceException("The Company cannot be updated," 
                    + " because the Company with \'" + id 
                    +"\' ID does not exist in database.");
        }
        deletedCompany.getEmployees().forEach(emp -> {
            employeeService.delete(emp);
        });
        
        try {
            companyDAO.delete(deletedCompany);
        } catch (IllegalArgumentException ex) {
            throw new ServiceException("The Company cannot be deleted, because " 
                    +  deletedCompany +  " is not a Company object.", ex);
        } catch (javax.persistence.TransactionRequiredException exc) { 
            throw new ServiceException("The \"" + deletedCompany 
                    +"\" cannot be deleted, NO Transaction.", exc);
        }
    }
    
    public Observable<EmployeeViewModel> randomEmployee(int id)  {
        return companyClient.findRandomEmployee()
                .map(exteranalEmployee -> 
                    createRandomEmployee(id, exteranalEmployee))
                            .toObservable();
    }
      
    public EmployeeViewModel createRandomEmployee(int id, 
            ExternalEmployee externalEmployee) {
        TransactionTemplate transaction = 
                new TransactionTemplate(transactionManager);
        return transaction.execute(status -> {
            Company company = companyDAO.getById(id);
            if (company == null) {
                throw new ServiceException("The Company cannot be updated, " 
                        + "because the Company with'" + id 
                        + "' ID does not exist in database.");
            }
            try {
                Employee randomEmployee = employeeService
                        .getOrCreateAndGet(externalEmployee);
                company.getEmployees().add(randomEmployee);
                companyDAO.update(company);
                return employeeConvertor.convert(randomEmployee);
            } catch (ClientException clEx) {
                throw new ServiceException(
                        "Problem with the working of the client", clEx);
            } catch (ServiceException serEx) {
                throw new ServiceException(
                        "A random employee does not create in the DB", serEx);
            }
        });
    }
}
