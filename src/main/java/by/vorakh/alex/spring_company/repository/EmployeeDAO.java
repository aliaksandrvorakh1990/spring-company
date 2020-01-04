package by.vorakh.alex.spring_company.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import by.vorakh.alex.spring_company.repository.entity.Employee;
import by.vorakh.alex.spring_company.repository.entity.JobTitle;
import by.vorakh.alex.spring_company.repository.entity.PersonalData;
import by.vorakh.alex.spring_company.repository.entity.Skill;

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
	    employeeList.add(entityManager.find(Employee.class, employeeId));
	}
	
	return employeeList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Employee> getAll(Skill skill) {
	 Query query = entityManager.createQuery("select e from Employee e "
	 	+ "WHERE :skill in elements(e.skillList)");
	 return (List<Employee>) query.setParameter("skill", skill).getResultList();
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
    
    public void delete(PersonalData personalData) {
	Query query = entityManager.createQuery("DELETE FROM Employee e WHERE e.personalData = :p");
	query.setParameter("p", personalData).executeUpdate();
    }
    
    public void delete(JobTitle jobTitle) {
	Query query = entityManager.createQuery("DELETE FROM Employee e WHERE e.jobTitle = :j");
	query.setParameter("j", jobTitle).executeUpdate();
    }
}