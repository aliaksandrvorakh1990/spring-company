package by.vorakh.alex.spring_company.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.model.PersonalDataPayload;
import by.vorakh.alex.spring_company.repository.PersonalDataDAO;
import by.vorakh.alex.spring_company.repository.entity.PersonalData;

@Service
public class PersonalDataService implements ServiceInterface<PersonalData, PersonalDataPayload>{

    @Autowired
    private PersonalDataDAO personalDataDAO;
    
    @Override
    public List<PersonalData> getAll() {
	return personalDataDAO.getAll();
    }

    @Override
    public PersonalData getById(int id) {
	return personalDataDAO.getById(id);
    }


    @Transactional
    public void create(PersonalData object) {
	personalDataDAO.create(object);
    }

   
    @Transactional
    public void update(PersonalData object) {
	personalDataDAO.update(object);
    }
    
    @Override
    @Transactional
    public void update(int id, PersonalDataPayload objectForEdit) {
	PersonalData personalDataForEditing = personalDataDAO.getById(id);
	personalDataForEditing.setFirstAndLastName(objectForEdit);
	personalDataDAO.update(personalDataForEditing);
    }

    @Override
    @Transactional
    public void delete(int id) {
	PersonalData deletedPersonalData = personalDataDAO.getById(id);
	personalDataDAO.delete(deletedPersonalData);
    }

    @Override
    public void create(PersonalDataPayload newPayload) {
	// TODO Auto-generated method stub
	
    }

}
