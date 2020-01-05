package by.vorakh.alex.spring_company.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.converter.PersonalDataToPersonalDataViewModelConverter;
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
    
    @Override
    @Transactional
    public IdViewModel create(PersonalDataPayload newPayload) {
	return new IdViewModel()
		.setId(personalDataDAO
			.create(new PersonalData(newPayload.getFirstName(), newPayload.getLastName())));
    }
    
    @Override
    @Transactional
    public void update(PersonalDataPayload editedPayload) {
	PersonalData personalDataForEditing = personalDataDAO.getById(editedPayload.getId());
	personalDataForEditing
		.setFirstName(editedPayload.getFirstName())
		.setLastName(editedPayload.getLastName());
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
