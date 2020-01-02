package by.vorakh.alex.spring_company.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import by.vorakh.alex.spring_company.repository.entity.Employee;

@Repository
public class EmployeeDAO implements DAO<Employee> {
    
    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> getAll() {
	return (List<Employee>) entityManager.createQuery("select e from Employee e").getResultList();
    }

    public List<Employee> getAll(List<Integer> employeeIdList) {
	List<Employee> employeeList = new ArrayList<Employee>();
	
	for (Integer employeeId : employeeIdList) {
	    entityManager.find(Employee.class, employeeId);
	}
	
	return employeeList;
    }
    
    @Override
    public Employee getById(int id) {
	return entityManager.find(Employee.class, id);
    }

    @Override
    public void create(Employee object) {
	entityManager.persist(object);
    }

    @Override
    public void update(Employee object) {
	entityManager.merge(object);
    }

    @Override
    public void delete(Employee object) {
	entityManager.remove(object);
    }
    
}