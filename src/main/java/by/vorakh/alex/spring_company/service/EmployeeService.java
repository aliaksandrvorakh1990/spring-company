package by.vorakh.alex.spring_company.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.converter.EmployeeOutsourceToEmployeeConverter;
import by.vorakh.alex.spring_company.converter.EmployeeToEmployeeViewModelConverter;
import by.vorakh.alex.spring_company.model.outsource.EmployeeOutsource;
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
	List<EmployeeViewModel>  employeeViewModelList= new ArrayList<EmployeeViewModel>();
	employeeDAO.getAll().forEach(employee -> {
	    employeeViewModelList.add(convertor.convert(employee));
	});
	return employeeViewModelList;
    }

    @Override
    public EmployeeViewModel getById(int id) {
	return convertor.convert(employeeDAO.getById(id));
    }
    
    public List<Employee> findListByIDs(List<Integer> employeeIDList) {
	List<Employee> list = new ArrayList<Employee>();
	for (Integer employeeId : employeeIDList) {
	    list.add(employeeDAO.getById(employeeId)) ;
	}
	return list;
    }
    
    @SuppressWarnings("finally")
    @Override
    @Transactional
    public IdViewModel create(EmployeePayload newPayload) {
	int createdID = -1;
	PersonalData personalDataById = personalDataService.findById(newPayload.getPersonalDataId());
	
	if (personalDataById == null) {
	    throw new ServiceException("The employee cannot be created, because the personal data with \'"+ 
		    newPayload.getPersonalDataId() +"\' ID does not exist in database.");
	}
	if (employeeDAO.isContained(personalDataById)) {
	    throw new ServiceException("The employee cannot be created, because the employee with \'"+ 
		    newPayload.getPersonalDataId() +"\' ID of the personal data exists in database.");
	}
	
	JobTitle jobTitleById = jobTitleService.findById(newPayload.getJobTitleId());
	
	if (jobTitleById == null) {
	    throw new ServiceException("The employee cannot be created, because the job title with \'"+ 
		    newPayload.getJobTitleId() +"\' ID does not exist in database.");
	}
	
	List<Skill> skillListByIds = skillService.getListBy(newPayload.getSkillIdsList());
	
	Employee newEmployee = new Employee(personalDataById, jobTitleById, skillListByIds);
	
	try {
	    createdID = employeeDAO.create(newEmployee);
	} catch (EntityExistsException e) {
	    throw new ServiceException("The \"" + newEmployee.toString() +  
		    "\" cannot be created, because to exist in database.", e);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The EMPLOYEE cannot be created, because " + 
		    newEmployee.toString() +  " is not a EMPLOYEE object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + newEmployee.toString() +  
		    "\" cannot be created, NO Transaction.", exc);
	} catch (javax.persistence.PersistenceException ex1) { 
	    throw new ServiceException("The \"" + newEmployee.toString() +  
		    "\" cannot be created, the database is not updated.", ex1);
	} finally {
	    return new IdViewModel(createdID);
	}

    } 
    
    public Employee getOrCreateAndGet(EmployeeOutsource externalSource) {
	Employee newEmployee = extermalSourceConvertor.convert(externalSource);
	
	PersonalData personalData = personalDataService.createAndGetWithId(newEmployee.getPersonalData());
	
	if (employeeDAO.isContained(personalData)) {
	    throw new ServiceException("The employee cannot be created, because the employee with \'"+ 
		    personalData.getId() +"\' ID of the personal data exists in database.");
	}
	
	newEmployee.setPersonalData(personalData);
	
	JobTitle jobTitle = jobTitleService.getOrCreateAndGetWithId(newEmployee.getJobTitle());
	
	newEmployee.setJobTitle(jobTitle);
	
	newEmployee.getSkillList().forEach(skill -> {
	    skillService.getOrCreateAndGetWithId(skill);
	});
	
	try {
	    newEmployee = employeeDAO.createAndGet(newEmployee);
	} catch (EntityExistsException e) {
	    throw new ServiceException("The \"" + newEmployee.toString() +  
		    "\" cannot be created, because to exist in database.", e);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The EMPLOYEE cannot be created, because " + 
		    newEmployee.toString() +  " is not a EMPLOYEE object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + newEmployee.toString() +  
		    "\" cannot be created, NO Transaction.", exc);
	} catch (javax.persistence.PersistenceException ex1) { 
	    throw new ServiceException("The \"" + newEmployee.toString() +  
		    "\" cannot be created, the database is not updated.", ex1);
	} 
	
	return newEmployee;
    }

    @Override
    @Transactional
    public void update(EmployeePayload editedPayload) {
	Employee editedEmployee = employeeDAO.getById(editedPayload.getId());
	if (editedEmployee == null) {
	    throw new ServiceException("The employee cannot be updated, because the employee with \'"+ 
		    editedPayload.getPersonalDataId() +"\' ID does not exist in database.");
	}
	
	PersonalData personalDataById = personalDataService.findById(editedPayload.getPersonalDataId()); 
	
	if (personalDataById == null) {
	    throw new ServiceException("The employee cannot be updated, because the personal data with \'"+ 
		    editedPayload.getPersonalDataId() +"\' ID does not exist in database.");
	}

	
	JobTitle jobTitleById = jobTitleService.findById(editedPayload.getJobTitleId());
	
	if (jobTitleById == null) {
	    throw new ServiceException("The employee cannot be updated, because the job title with \'"+ 
		    editedPayload.getJobTitleId() +"\' ID does not exist in database.");
	}
	
	List<Skill> skillListByIds = skillService.getListBy(editedPayload.getSkillIdsList());
	
	editedEmployee
		.setPersonalData(personalDataById)
		.setJobTitle(jobTitleById)
		.setSkillList(skillListByIds);
	try {
	    employeeDAO.update(editedEmployee);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The employee cannot be updated, because " + 
		    editedEmployee.toString() +  " is not a employee object.", ex);
	} catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + editedEmployee.toString() +  
		    "\" cannot be updated, NO Transaction.", exc);
	}
    }
    
    @Transactional
    public void removeDeletedSkillfromSkillLists(Skill skill) {
	employeeDAO.getAll(skill).forEach(emp -> {
	    emp.getSkillList().remove(skill);
	    update(emp);
	});
    }
    
    @Transactional
    public void update(Employee editedEmployee) {
	try {
	    employeeDAO.update(editedEmployee);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The employee cannot be updated, because " + 
		    editedEmployee.toString() +  " is not a employee object.", ex);
	} catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + editedEmployee.toString() +  
		    "\" cannot be updated, NO Transaction.", exc);
	}
    }

    @Override
    @Transactional
    public void delete(int id) {
	Employee deletedEmployee = employeeDAO.getById(id);
	
	if (deletedEmployee == null) {
	    throw new ServiceException("The employee cannot be deleted, because the employee with \'"+ 
		    id +"\' ID does not exist in database.");
	}
	
	delete(deletedEmployee);
    }
    
    @Transactional
    public void delete(Employee deletedEmployee) {
	deletedEmployee.getSkillList().clear();
	
	update(deletedEmployee);
	
	try {
	    employeeDAO.delete(deletedEmployee);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The employee cannot be deleted, because " + 
		    deletedEmployee.toString() +  " is not a employee object.", ex);
	} catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + deletedEmployee.toString() +  
		    "\" cannot be deleted, NO Transaction.", exc);
	}
    }
    
    
    public void delete(PersonalData personalData) {
	try {
	    employeeDAO.delete(personalData);
	} catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The Employee with \"" + personalData.toString() +  
		    "\" cannot be deleted, NO Transaction.", exc);
	}
    }

}