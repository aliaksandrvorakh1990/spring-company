package by.vorakh.alex.spring_company.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.converter.EmployeeOutsourceToEmployeeConverter;
import by.vorakh.alex.spring_company.converter.EmployeeToEmployeeViewModelConverter;
import by.vorakh.alex.spring_company.model.external.ExternalEmployee;
import by.vorakh.alex.spring_company.model.payload.EmployeePayload;
import by.vorakh.alex.spring_company.model.view_model.EmployeeViewModel;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.entity.Employee;
import by.vorakh.alex.spring_company.repository.entity.JobTitle;
import by.vorakh.alex.spring_company.repository.entity.PersonalData;
import by.vorakh.alex.spring_company.repository.entity.Skill;

@Service
public class EmployeeService implements ServiceInterface<EmployeeViewModel, EmployeePayload> {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private PersonalDataService personalDataService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private JobTitleService jobTitleService;
    @Autowired
    private EmployeeToEmployeeViewModelConverter convertor;
    @Autowired
    private EmployeeOutsourceToEmployeeConverter extermalSourceConvertor;

    @Override
    public List<EmployeeViewModel> getAll() {
      	List<EmployeeViewModel>  employeeViewModelList= new ArrayList<>();
      	employeeDAO.getAll().forEach(employee -> {
      	    employeeViewModelList.add(convertor.convert(employee));
      	});
        return employeeViewModelList;
    }

    @Override
    public EmployeeViewModel getById(int id) {
        return convertor.convert(employeeDAO.getById(id));
    }
    
    public Set<Employee> findListByIDs(List<Integer> employeeIDList) {
      	Set<Employee> list = new LinkedHashSet<>();
      	for (Integer employeeId : employeeIDList) {
      	    list.add(employeeDAO.getById(employeeId)) ;
      	}
      	return list;
    }
    
    @Override
    @Transactional
    public IdViewModel create(EmployeePayload newPayload) {
        int persDataID = newPayload.getPersonalDataId();
      	PersonalData personalDataById = personalDataService.findById(persDataID);
      	
      	if (personalDataById == null) {
      	    throw new ServiceException("The employee cannot be created, because" 
                    + " the personal data with \'" +  persDataID 
                    +"\' ID does not exist in database.");
      	}
      	if (employeeDAO.isContained(personalDataById)) {
      	    throw new ServiceException("The employee cannot be created, because" 
                    + " the employee with \'"+ persDataID 
                    +"\' ID of the personal data exists in database.");
      	}
      	
        int jodId = newPayload.getJobTitleId();
      	JobTitle jobTitleById = jobTitleService.findById(jodId);
      	
      	if (jobTitleById == null) {
      	    throw new ServiceException("The employee cannot be created, because" 
                    + " the job title with \'"+   jobTitleById 
                    + "\' ID does not exist in database.");
      	}
      	
      	List<Skill> skillListByIds = skillService
                .getListBy(newPayload.getSkillIdsList());
      	
      	Employee newEmployee = new Employee(personalDataById, jobTitleById, skillListByIds);
      	
      	try {
      	    int createdID = employeeDAO.create(newEmployee);
      	    return new IdViewModel(createdID);
      	} catch (EntityExistsException e) {
      	    throw new ServiceException("The \"" + newEmployee + "\" cannot" 
                    + " be created, because to exist in database.", e);
      	} catch (IllegalArgumentException ex) {
      	    throw new ServiceException("The EMPLOYEE cannot be created, because " 
                    + newEmployee +  " is not a EMPLOYEE object.", ex);
      	}  catch (TransactionRequiredException exc) { 
      	    throw new ServiceException("The \"" + newEmployee + "\" cannot " 
                    + "be created, NO Transaction.", exc);
      	} catch (PersistenceException ex1) { 
      	    throw new ServiceException("The \"" + newEmployee + "\" cannot" 
                    + " be created, the database is not updated.", ex1);
      	} 
    } 
    
    @Transactional
    public Employee getOrCreateAndGet(ExternalEmployee externalSource) {
      	Employee newEmployee = extermalSourceConvertor.convert(externalSource);
      	
      	PersonalData personalData = personalDataService
                .createAndGetWithId(newEmployee.getPersonalData());
      	
      	if (employeeDAO.isContained(personalData)) {
      	    throw new ServiceException("The employee cannot be created, because" 
                    + " the employee with \'" +  personalData.getId() 
                    + "\' ID of the personal data exists in database.");
      	}
      	
      	newEmployee.setPersonalData(personalData);
      	
      	JobTitle jobTitle = jobTitleService
                .getOrCreateAndGetWithId(newEmployee.getJobTitle());
      	
      	newEmployee.setJobTitle(jobTitle);
      	
      	newEmployee.getSkills().forEach(skill -> {
      	    int id = skillService.getOrCreateAndGetWithId(skill).getId();
      	    skill.setId(id);
      	});
      	
      	try {
      	    newEmployee = employeeDAO.createAndGet(newEmployee);
      	    return newEmployee;
      	} catch (EntityExistsException e) {
      	    throw new ServiceException("The \"" + newEmployee + "\" cannot be" 
                    + " created, because to exist in database.", e);
      	} catch (IllegalArgumentException ex) {
      	    throw new ServiceException("The EMPLOYEE cannot be created, because " 
                    + newEmployee + " is not a EMPLOYEE object.", ex);
      	}  catch (TransactionRequiredException exc) { 
      	    throw new ServiceException("The \"" + newEmployee + "\" cannot be" 
                    + " created, NO Transaction.", exc);
      	} catch (PersistenceException ex1) { 
      	    throw new ServiceException("The \"" + newEmployee + "\" cannot " 
                    + "be created, the database is not updated.", ex1);
      	} 
    }

    @Override
    @Transactional
    public void update(EmployeePayload editedPayload) {
        int empID = editedPayload.getId();
      	Employee editedEmployee = employeeDAO.getById(empID);
      	if (editedEmployee == null) {
      	    throw new ServiceException("The employee cannot be updated, because" 
                    + " the employee with \'" +  empID 
                    + "\' ID does not exist in database.");
      	}
      	
        int persDataID = editedPayload.getPersonalDataId();
      	PersonalData personalDataById = personalDataService.findById(persDataID); 
      	
      	if (personalDataById == null) {
      	    throw new ServiceException("The employee cannot be updated, because " 
                    + " the personal data with \'" + persDataID 
                    + "\' ID does not exist in database.");
      	}
      
        int jobID = editedPayload.getJobTitleId();
      	JobTitle jobTitleById = jobTitleService.findById(jobID);
      	
      	if (jobTitleById == null) {
      	    throw new ServiceException("The employee cannot be updated, because" 
                    + " the job title with \'"+  jobID 
                    + "\' ID does not exist in database.");
      	}
      	
      	List<Skill> skillListByIds = skillService
                .getListBy(editedPayload.getSkillIdsList());
      	
      	editedEmployee
            		.setPersonalData(personalDataById)
            		.setJobTitle(jobTitleById)
            		.setSkills(skillListByIds);
      	try {
      	    employeeDAO.update(editedEmployee);
      	} catch (IllegalArgumentException ex) {
      	    throw new ServiceException("The employee cannot be updated, because " 
                    + editedEmployee +  " is not a employee object.", ex);
      	} catch (TransactionRequiredException exc) { 
      	    throw new ServiceException("The \"" + editedEmployee +  "\" cannot " 
                    + "be updated, NO Transaction.", exc);
      	}
    }
    
    @Transactional
    public void removeDeletedSkillfromSkillLists(Skill skill) {
      	employeeDAO.getAll(skill).forEach(emp -> {
      	    emp.getSkills().remove(skill);
      	    update(emp);
      	});
    }
    
    @Transactional
    public void update(Employee editedEmployee) {
      	try {
      	    employeeDAO.update(editedEmployee);
      	} catch (IllegalArgumentException ex) {
      	    throw new ServiceException("The employee cannot be updated, because " 
                    + editedEmployee +  " is not a employee object.", ex);
      	} catch (TransactionRequiredException exc) { 
      	    throw new ServiceException("The \"" + editedEmployee 
                    + "\" cannot be updated, NO Transaction.", exc);
      	}
    }

    @Override
    @Transactional
    public void delete(int id) {
      	Employee deletedEmployee = employeeDAO.getById(id);
      	
      	if (deletedEmployee == null) {
      	    throw new ServiceException("The employee cannot be deleted, because" 
                    + " the employee with \'"+ id 
                    +"\' ID does not exist in database.");
      	}
      	
      	delete(deletedEmployee);
    }
    
    @Transactional
    public void delete(Employee deletedEmployee) {
      	deletedEmployee.getSkills().clear();
      	
      	update(deletedEmployee);
      	
      	try {
      	    employeeDAO.delete(deletedEmployee);
      	} catch (IllegalArgumentException ex) {
      	    throw new ServiceException("The employee cannot be deleted, because " 
                    + deletedEmployee + " is not a employee object.", ex);
              
      	} catch (TransactionRequiredException exc) { 
      	    throw new ServiceException("The \"" + deletedEmployee 
                    +  "\" cannot be deleted, NO Transaction.", exc);
      	}
    }
    
    @Transactional
    public void delete(PersonalData personalData) {
      	try {
      	    employeeDAO.delete(personalData);
      	} catch (TransactionRequiredException exc) { 
      	    throw new ServiceException("The Employee with \"" + personalData 
                    +  "\" cannot be deleted, NO Transaction.", exc);
      	}
    }
}