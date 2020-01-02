package by.vorakh.alex.spring_company.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.model.EmployeePayload;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.JobTitleDAO;
import by.vorakh.alex.spring_company.repository.PersonalDataDAO;
import by.vorakh.alex.spring_company.repository.SkillDAO;
import by.vorakh.alex.spring_company.repository.entity.Employee;

@Service
public class EmployeeService implements ServiceInterface<Employee, EmployeePayload> {
    
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private PersonalDataDAO personalDataDAO;
    @Autowired
    private SkillDAO skillDAO;
    @Autowired
    private JobTitleDAO jobTitleDAO;

    @Override
    public List<Employee> getAll() {
	return employeeDAO.getAll();
    }

    @Override
    public Employee getById(int id) {
	return employeeDAO.getById(id);
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
	employeeDAO.delete(deletedEmployee);
    }

}