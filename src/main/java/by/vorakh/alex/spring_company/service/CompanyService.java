package by.vorakh.alex.spring_company.service;

import by.vorakh.alex.spring_company.client.ClientException;
import by.vorakh.alex.spring_company.client.RandomEntityClient;
import by.vorakh.alex.spring_company.converter.CompanyToCompanyViewModelConverter;
import by.vorakh.alex.spring_company.converter.EmployeeToEmployeeViewModelConverter;
import by.vorakh.alex.spring_company.model.outsource.EmployeeOutsource;
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

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService implements ServiceInterface<CompanyViewModel, CompanyPayload> {
    
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
    @Override
    public List<CompanyViewModel> getAll() {
	List<CompanyViewModel> companyViewModelList = new ArrayList<CompanyViewModel>();
	companyDAO.getAll().forEach(company -> 
		companyViewModelList.add(convertor.convert(company)));
	
        return companyViewModelList;
    }

    @Override
    public CompanyViewModel getById(int id) {
        return convertor.convert(companyDAO.getById(id));
    }

    @SuppressWarnings("finally")
    @Override
    @Transactional
    public IdViewModel create(CompanyPayload newPayload) {
	int createdID = -1;
	String name = newPayload.getName();
	if (companyDAO.isContained(name)) {
	    throw new ServiceException("The \"" + name + 
		    "\" company cannot be created, because to exist in database.");
	}
	
	List<Employee> employeeList = employeeService.findListByIDs(newPayload.getEmployeeIdList());
	
	Company newCompany =  new Company(name, employeeList);
	
	try {
	    createdID = companyDAO.create(newCompany);
	} catch (EntityExistsException e) {
	    throw new ServiceException("The \"" + newCompany.toString() +  
		    "\" cannot be created, because to exist in database.", e);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The COMPANY cannot be created, because " + 
		    newCompany.toString() +  " is not a COMPANY object.", ex);
	} catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + newCompany.toString() +  
		    "\" cannot be created, NO Transaction.", exc);
	} catch (javax.persistence.PersistenceException ex1) { 
	    throw new ServiceException("The \"" + newCompany.toString() +  
		    "\" cannot be created, the database is not updated.", ex1);
	} finally {
	    return new IdViewModel(createdID);
	}	
    }

    @Override
    @Transactional
    public void update(CompanyPayload editedPayload) {
	String name = editedPayload.getName();
	if (companyDAO.isContained(name)) {
	    throw new ServiceException("The \"" + name + 
		    "\" company cannot be updated, because to exist in database.");
	}
	Company editedCompany = companyDAO.getById(editedPayload.getId());
	if (editedCompany == null) {
	    throw new ServiceException("The Company cannot be updated, because the Company with \'"+ 
		    editedPayload.getId() +"\' ID does not exist in database.");
	}
	List<Employee> employeeList = employeeService.findListByIDs(editedPayload.getEmployeeIdList());
	editedCompany
		.setName(name)
		.setEmployeeList(employeeList);
	update(editedCompany);
	
    }
    
    @Transactional
    public void update(Company editedCompany) {
	try {
	    companyDAO.update(editedCompany);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The Company cannot be updated, because " + 
		    editedCompany.toString() +  " is not a Company object.", ex);
	} catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + editedCompany.toString() +  
		    "\" cannot be updated, NO Transaction.", exc);
	}
    }
    
    @Override
    @Transactional
    public void delete(int id) {
        Company deletedCompany = companyDAO.getById(id);
        if (deletedCompany == null) {
	    throw new ServiceException("The Company cannot be updated, because the Company with \'" + 
		    id +"\' ID does not exist in database.");
	}
        deletedCompany.getEmployeeList().forEach(emp -> {
            employeeService.delete(emp);
        });
        
        try {
            companyDAO.delete(deletedCompany);
        } catch (IllegalArgumentException ex) {
	    throw new ServiceException("The Company cannot be deleted, because " + 
		    deletedCompany.toString() +  " is not a Company object.", ex);
	} catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + deletedCompany.toString() +  
		    "\" cannot be deleted, NO Transaction.", exc);
	}
       
    }
    
    public Observable<EmployeeViewModel> randomEmployee(int id)  {
	return companyClient.findRandomEmployee().map(exteranalEmployee -> {
	    System.out.println("Service!!! " +exteranalEmployee);
	    
	    EmployeeViewModel emp =createRandomEmployee(id, exteranalEmployee);
	    
	    System.out.println("AFTER DAO " +emp);
	    return emp;
	}).toObservable();
    }
    
    @SuppressWarnings("finally")
    @Transactional
    private EmployeeViewModel createRandomEmployee(int id, EmployeeOutsource externalEmployee) {
	
	EmployeeViewModel employeeView = null;
	Company company;
	company = companyDAO.getById(id);
	 if (company == null) {
		    throw new ServiceException("The Company cannot be updated, because the Company with \'" + 
			    id +"\' ID does not exist in database.");
		}
     
	
	try {
	    Employee randomEmployee;
	
	    randomEmployee = employeeService.getOrCreateAndGet(externalEmployee);
	    // BAG
	    company.getEmployeeList().add(randomEmployee);
	    
	    companyDAO.update(company);
	    employeeView = employeeConvertor.convert(randomEmployee);
	    System.out.println(employeeView);
	} catch (ClientException clEx) {
	    throw new ServiceException("Problem with the working of the client", clEx);
	} catch (ServiceException serEx) {
	    throw new ServiceException("A random employee does not create in the DB", serEx);
	}finally {
	    return employeeView;
	}
    }
}
