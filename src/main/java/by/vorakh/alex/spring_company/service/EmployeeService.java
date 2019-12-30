package by.vorakh.alex.spring_company.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.model.EmployeePayload;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.entity.Employee;

@Service
public class EmployeeService implements ServiceInterface<Employee, EmployeePayload> {
    
    @Autowired
    private EmployeeDAO employeeDAO;

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
	employeeDAO.create(new Employee(newPayload));
    }

    @Override
    @Transactional
    public void update(int id, EmployeePayload editedPayload) {
	Employee editedEmployee = employeeDAO.getById(id);
	editedEmployee.set(editedPayload);
	employeeDAO.update(editedEmployee);
    }

    @Override
    @Transactional
    public void delete(int id) {
	Employee deletedEmployee = employeeDAO.getById(id);
	employeeDAO.delete(deletedEmployee);
    }

}