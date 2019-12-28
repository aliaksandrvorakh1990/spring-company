package by.vorakh.alex.spring_company.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.repository.PersonalDataDAO;
import by.vorakh.alex.spring_company.repository.entity.PersonalData;

@Service
public class PersonalDataService implements ServiceInterface<PersonalData>{

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

    @Override
    @Transactional
    public void create(PersonalData object) {
	personalDataDAO.create(object);
	
    }

    @Override
    @Transactional
    public void update(PersonalData object) {
	personalDataDAO.update(object);
    }
    
    @Override
    public void update(int id, PersonalData editedObject) {
	// TODO Auto-generated method stub
	
    }

    @Override
    @Transactional
    public void delete(int id) {
	PersonalData deletedPersonalData = personalDataDAO.getById(id);
	personalDataDAO.create(deletedPersonalData);
    }

    

}
