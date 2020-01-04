package by.vorakh.alex.spring_company.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.converter.EntityToViewModelConverter;
import by.vorakh.alex.spring_company.model.payload.EmployeePayload;
import by.vorakh.alex.spring_company.model.view_model.EmployeeViewModel;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.JobTitleDAO;
import by.vorakh.alex.spring_company.repository.PersonalDataDAO;
import by.vorakh.alex.spring_company.repository.SkillDAO;
import by.vorakh.alex.spring_company.repository.entity.Employee;

@Service
public class EmployeeService implements ServiceInterface<EmployeeViewModel, EmployeePayload> {
    
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private PersonalDataDAO personalDataDAO;
    @Autowired
    private SkillDAO skillDAO;
    @Autowired
    private JobTitleDAO jobTitleDAO;
    @Autowired
    private EntityToViewModelConverter convertor;

    @Override
    public List<EmployeeViewModel> getAll() {
	List<EmployeeViewModel>  employeeViewModelList= new ArrayList<EmployeeViewModel>();
	employeeDAO.getAll().forEach(employee -> {
	    employeeViewModelList.add(convertor.converte(employee));
	});
	return employeeViewModelList;
    }

    @Override
    public EmployeeViewModel getById(int id) {
	return convertor.converte(employeeDAO.getById(id));
    }

    @Override
    @Transactional
    public void create(EmployeePayload newPayload) {
	employeeDAO.create(new Employee(
		personalDataDAO.getById(newPayload.getPersonalDataId()), 
		jobTitleDAO.getById(newPayload.getJobTitleId()), 
		skillDAO.getAll(newPayload.getSkillIdsList()))
		);
    }

    @Override
    @Transactional
    public void update(int id, EmployeePayload editedPayload) {
	Employee editedEmployee = employeeDAO.getById(id);
	editedEmployee.setPersonalData(personalDataDAO.getById(editedPayload.getPersonalDataId()))
	.setJobTitle(jobTitleDAO.getById(editedPayload.getJobTitleId()))
	.setSkillList(skillDAO.getAll(editedPayload.getSkillIdsList()));
	employeeDAO.update(editedEmployee);
    }

    @Override
    @Transactional
    public void delete(int id) {
	Employee deletedEmployee = employeeDAO.getById(id);
	deletedEmployee.getSkillList().clear();
	employeeDAO.update(deletedEmployee);
	employeeDAO.delete(deletedEmployee);
    }

}