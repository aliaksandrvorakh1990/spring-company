package by.vorakh.alex.spring_company.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.converter.EntityToViewModelConverter;
import by.vorakh.alex.spring_company.model.payload.PersonalDataPayload;
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
    private EntityToViewModelConverter convertor;
    
    @Override
    public List<PersonalDataViewModel> getAll() {
	List<PersonalDataViewModel> personalDataViewModellist = new ArrayList<PersonalDataViewModel>();
	personalDataDAO.getAll().forEach(personalData -> {
	    personalDataViewModellist.add(convertor.converte(personalData));
	});
	
	return personalDataViewModellist;
    }

    @Override
    public PersonalDataViewModel getById(int id) {
	return convertor.converte(personalDataDAO.getById(id));
    }
    
    @Override
    @Transactional
    public void create(PersonalDataPayload newPayload) {
	personalDataDAO.create(new PersonalData(newPayload.getFirstName(), newPayload.getLastName()));
    }
    
    @Override
    @Transactional
    public void update(int id, PersonalDataPayload objectForEdit) {
	PersonalData personalDataForEditing = personalDataDAO.getById(id);
	personalDataForEditing.setFirstName(objectForEdit.getFirstName());
	personalDataForEditing.setLastName(objectForEdit.getLastName());
	personalDataDAO.update(personalDataForEditing);
    }

    @Override
    @Transactional
    public void delete(int id) {
	PersonalData deletedPersonalData = personalDataDAO.getById(id);
	employeeDAO.delete(deletedPersonalData);
	personalDataDAO.delete(deletedPersonalData);
    }

}
