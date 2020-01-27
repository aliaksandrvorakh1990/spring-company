package by.vorakh.alex.spring_company.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.converter.PersonalDataOutsourceToPersonalDataConverter;
import by.vorakh.alex.spring_company.converter.PersonalDataToPersonalDataViewModelConverter;
import by.vorakh.alex.spring_company.model.outsource.PersonalDataOutsource;
import by.vorakh.alex.spring_company.model.payload.PersonalDataPayload;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.model.view_model.PersonalDataViewModel;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.PersonalDataDAO;
import by.vorakh.alex.spring_company.repository.entity.PersonalData;

@Service
public class PersonalDataService implements ServiceInterface<PersonalDataViewModel, PersonalDataPayload>{

    @Autowired
    private PersonalDataDAO personalDataDAO;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private PersonalDataToPersonalDataViewModelConverter convertor;
    @Autowired
    private PersonalDataOutsourceToPersonalDataConverter extermalSourceConvertor;
    
    @Override
    public List<PersonalDataViewModel> getAll() {
	List<PersonalDataViewModel> personalDataViewModellist = new ArrayList<PersonalDataViewModel>();
	personalDataDAO.getAll().forEach(personalData -> {
	    personalDataViewModellist.add(convertor.convert(personalData));
	});
	
	return personalDataViewModellist;
    }

    @Override
    public PersonalDataViewModel getById(int id) {
	return convertor.convert(personalDataDAO.getById(id));
    }
    
    @SuppressWarnings("finally")
    @Override
    @Transactional
    public IdViewModel create(PersonalDataPayload newPayload) {
	int createdID = -1;
	
	PersonalData newPersonalData = new PersonalData(newPayload.getFirstName(), newPayload.getLastName());
	try {
	    
	    createdID= personalDataDAO
			.create(newPersonalData);
	} catch (EntityExistsException e) {
	    throw new ServiceException("The \"" + newPersonalData.getFirstName() + 
    		    " " + newPersonalData.getLastName() +  
		    "\" cannot be created, because to exist in database.", e);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The PERSONAL_DATA cannot be created, because " + 
		    newPersonalData.toString() +  " is not a PERSONAL_DATA object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + newPersonalData.getFirstName() + 
    		    " " + newPersonalData.getLastName() +  
		    "\" cannot be created, NO Transaction.", exc);
	} catch (javax.persistence.PersistenceException ex1) { 
	    throw new ServiceException("The \"" + newPersonalData.getFirstName() + 
    		    " " + newPersonalData.getLastName() +  
		    "\" cannot be created, the database is not updated.", ex1);
	} finally {
	    return new IdViewModel(createdID);
	}
    }
    
    @Transactional
    public PersonalDataViewModel createAndGet(PersonalDataOutsource extemalSource) {
	PersonalData newPersonalData = extermalSourceConvertor.convert(extemalSource);
	return createAndGet(newPersonalData);
    }

    @SuppressWarnings("finally")
    @Transactional
    public PersonalDataViewModel createAndGet(PersonalData newPersonalData) {
	PersonalData returnedPersonalData = null;
    	try { 
    	    returnedPersonalData = personalDataDAO.createAndGet(newPersonalData);
    	} catch (EntityExistsException e) {
    	    throw new ServiceException("The \"" + newPersonalData.getFirstName() + 
    		    " " + newPersonalData.getLastName() + 
    		    "\" cannot be created, because to exist in database.", e);
	} catch (IllegalArgumentException ex) {
    	    throw new ServiceException("The PERSONAL_DATA cannot be created, because " + 
    		    newPersonalData.toString() +  " is not a Skill object.", ex);
    	} catch (javax.persistence.TransactionRequiredException exc) { 
    	    throw new ServiceException("The \"" + newPersonalData.getFirstName() + 
    		    " " + newPersonalData.getLastName() +  
    		    "\" cannot be created, NO Transaction.", exc);
    	} catch (javax.persistence.PersistenceException ex1) { 
    	    throw new ServiceException("The \"" + newPersonalData.getFirstName() + 
    		    " " + newPersonalData.getLastName() +  
    		    "\" cannot be created, the database is not updated.", ex1);
    	} finally {
    	    return convertor.convert(returnedPersonalData);
    	}
    }
    
    @Override
    @Transactional
    public void update(PersonalDataPayload editedPayload) {
	PersonalData personalDataForEditing = personalDataDAO.getById(editedPayload.getId());
	personalDataForEditing
		.setFirstName(editedPayload.getFirstName())
		.setLastName(editedPayload.getLastName());
	try {
	    personalDataDAO.update(personalDataForEditing);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The PERSONAL_DATA cannot be updated, because " + 
		    personalDataForEditing.toString() +  " is not a Skill object.", ex);
	} catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + personalDataForEditing.getFirstName() + 
    		    " " + personalDataForEditing.getLastName() +  
		    "\" cannot be updated, NO Transaction.", exc);
	}
    }

    @Override
    @Transactional
    public void delete(int id) {
	PersonalData deletedPersonalData = personalDataDAO.getById(id);
	if (deletedPersonalData == null) {
	    throw new ServiceException("The PERSONAL_DATA with \'" + id + 
		    "\' ID cannot be deleted, because to no exist in database");
	}
	employeeDAO.delete(deletedPersonalData);
	try {
	    personalDataDAO.delete(deletedPersonalData);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The PERSONAL_DATA cannot be deleted, because " + 
		    deletedPersonalData.toString() +  " is not a Skill object.", ex);
	} catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + deletedPersonalData.getFirstName() + 
    		    " " + deletedPersonalData.getLastName() +  
		    "\" cannot be deleted, NO Transaction.", exc);
	}
    }

}
