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
        return entityManager.createQuery("select e from Employee e")
                .getResultList();
    }
    
    public List<Employee> getAll(List<Integer> employeeIdList) {
      	List<Employee> employeeList = new ArrayList<>();
      	
      	for (Integer employeeId : employeeIdList) {
      	    employeeList.add(entityManager.find(Employee.class, employeeId));
      	}
      	
      	return employeeList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Employee> getAll(Skill skill) {
        Query query = entityManager.createQuery("select e from Employee e " + 
                "WHERE :skill in elements(e.skillList)");
        return query.setParameter("skill", skill).getResultList();
    }
    
    @Override
    public Employee getById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public int create(Employee object) {
      	entityManager.persist(object);
      	entityManager.flush();
      	return object.getId();
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
      	Query query = entityManager.createQuery("DELETE FROM Employee e " + 
                "WHERE e.personalData = :p");
      	query.setParameter("p", personalData).executeUpdate();
    }
    
    public void delete(JobTitle jobTitle) {
      	Query query = entityManager.createQuery("DELETE FROM Employee e " + 
                "WHERE e.jobTitle = :j");
      	query.setParameter("j", jobTitle).executeUpdate();
    }

    @Override
    public boolean isContained(Employee object) {
        return findExisted(object) != null;
    }
    
    public boolean isContained(PersonalData personalData) {
   	    return findExisted(personalData) != null;
    }

    @Override
    public Employee createAndGet(Employee object) {
      	entityManager.persist(object);
      	entityManager.flush();
      	return object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Employee findExisted(Employee object) {
      	Query query = entityManager.createQuery("select e from Employee e "
      	 	+ "WHERE e.personalData = :p");
      	query.setParameter("p", object.getPersonalData());
      	return (Employee) query.getResultList().stream()
                .findFirst()
                .orElse(null);
    }
    
    @SuppressWarnings("unchecked")
    public Employee findExisted(PersonalData personalData) {
      	Query query = entityManager.createQuery("select e from Employee e "	+ 
                "WHERE e.personalData = :p");
      	query.setParameter("p", personalData);
      	return (Employee) query.getResultList().stream()
                .findFirst()
                .orElse(null);
    }
}