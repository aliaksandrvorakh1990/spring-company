package by.vorakh.alex.spring_company.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.converter.PersonalDataOutsourceToPersonalDataConverter;
import by.vorakh.alex.spring_company.converter.PersonalDataToPersonalDataViewModelConverter;
import by.vorakh.alex.spring_company.model.external.ExternalPersonalData;
import by.vorakh.alex.spring_company.model.payload.PersonalDataPayload;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.model.view_model.PersonalDataViewModel;
import by.vorakh.alex.spring_company.repository.PersonalDataDAO;
import by.vorakh.alex.spring_company.repository.entity.PersonalData;

@Service
public class PersonalDataService implements 
        ServiceInterface<PersonalDataViewModel, PersonalDataPayload>{
    @Autowired
    private PersonalDataDAO personalDataDAO;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PersonalDataToPersonalDataViewModelConverter convertor;
    @Autowired
    private PersonalDataOutsourceToPersonalDataConverter extermalSourceConvertor;
    
    @Override
    public List<PersonalDataViewModel> getAll() {
      	List<PersonalDataViewModel> personalDataViewModellist = 
                new ArrayList<>();
      	personalDataDAO.getAll().forEach(personalData -> {
      	    personalDataViewModellist.add(convertor.convert(personalData));
      	});
      	
      	return personalDataViewModellist;
    }

    @Override
    public PersonalDataViewModel getById(int id) {
        return convertor.convert(findById(id));
    }
    
    public PersonalData findById(int id) {
        return personalDataDAO.getById(id);
    }
    
    
    @Override
    @Transactional
    public IdViewModel create(PersonalDataPayload newPayload) {
      	int createdID;
      	
      	PersonalData newPersonalData;
      	
      	newPersonalData = new PersonalData(newPayload.getFirstName(), 
                newPayload.getLastName());
      	
      	try {
      	    createdID= personalDataDAO.create(newPersonalData);
      	    return new IdViewModel(createdID);
      	} catch (EntityExistsException e) {
      	    throw new ServiceException("The \"" + newPersonalData + "\" cannot " 
                    + "be created, because to exist in database.", e);
      	} catch (IllegalArgumentException ex) {
      	    throw new ServiceException("The PERSONAL_DATA cannot be created, " 
                    + "because " + newPersonalData 
                    + " is not a PERSONAL_DATA .", ex);
      	} catch (javax.persistence.TransactionRequiredException exc) { 
      	    throw new ServiceException("The \"" + newPersonalData 
                    + "\" cannot be created, NO Transaction.", exc);
      	} catch (javax.persistence.PersistenceException ex1) { 
      	    throw new ServiceException("The \"" + newPersonalData + "\" cannot" 
                    + " be created, the database is not updated.", ex1);
      	} 
    }
    
    @Transactional
    public PersonalDataViewModel createAndGet(
            ExternalPersonalData extemalSource) {
      	PersonalData newPersonalData = extermalSourceConvertor
                .convert(extemalSource);
      	return createAndGet(newPersonalData);
    }

    @Transactional
    public PersonalDataViewModel createAndGet(PersonalData newPersonalData) {
	       return convertor.convert(createAndGetWithId(newPersonalData));
    }
  
    @Transactional
    public PersonalData createAndGetWithId(PersonalData newPersonalData) {
      	try {
      	    return personalDataDAO.createAndGet(newPersonalData);
      	} catch (EntityExistsException e) {
      	    throw new ServiceException("The \"" + newPersonalData +  "\" cannot" 
                    + " be created, because to exist in database.", e);
        } catch (IllegalArgumentException ex) {
      	    throw new ServiceException("The PERSONAL_DATA cannot be created, " 
                    + "because " + newPersonalData +" is not a Skill.", ex);
      	} catch (javax.persistence.TransactionRequiredException exc) { 
      	    throw new ServiceException("The \"" + newPersonalData + "\" cannot " 
                    + "be created, NO Transaction.", exc);
      	} catch (javax.persistence.PersistenceException ex1) { 
      	    throw new ServiceException("The \"" + newPersonalData + "\" cannot " 
                    + "be created, the database is not updated.", ex1);
      	} 
    }
    
    @Override
    @Transactional
    public void update(PersonalDataPayload editedPayload) {
      	PersonalData personalDataForEditing = personalDataDAO
                .getById(editedPayload.getId());
      	personalDataForEditing
      		      .setFirstName(editedPayload.getFirstName())
      		      .setLastName(editedPayload.getLastName());
      	try {
      	    personalDataDAO.update(personalDataForEditing);
      	} catch (IllegalArgumentException ex) {
      	    throw new ServiceException("The PERSONAL_DATA cannot be updated, " 
                    + "because " + personalDataForEditing 
                    + " is not a PERSONAL_DATA object.", ex);
      	} catch (javax.persistence.TransactionRequiredException exc) { 
      	    throw new ServiceException("The \"" + personalDataForEditing 
                    + "\" cannot be updated, NO Transaction.", exc);
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
        
      	employeeService.delete(deletedPersonalData);
      	try {
      	    personalDataDAO.delete(deletedPersonalData);
      	} catch (IllegalArgumentException ex) {
      	    throw new ServiceException("The PERSONAL_DATA cannot be deleted," 
                    + " because " + deletedPersonalData 
                    + " is not a PERSONAL_DATA object.", ex);
      	} catch (javax.persistence.TransactionRequiredException exc) { 
      	    throw new ServiceException("The \"" + deletedPersonalData
                    +  "\" cannot be deleted, NO Transaction.", exc);
      	}
    }
}
