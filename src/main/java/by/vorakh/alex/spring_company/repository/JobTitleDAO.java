package by.vorakh.alex.spring_company.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import by.vorakh.alex.spring_company.repository.entity.JobTitle;

@Repository
public class JobTitleDAO implements DAO<JobTitle> {
    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<JobTitle> getAll() {
        return entityManager.createQuery("select j from JobTitle j")
		            .getResultList();
    }

    @Override
    public JobTitle getById(int id) {
        return entityManager.find(JobTitle.class, id);
    }

    @Override
    public int create(JobTitle object) {
      	entityManager.persist(object);
      	entityManager.flush();
      	return object.getId();
    }

    @Override
    public void update(JobTitle object) {
        entityManager.merge(object);
    }

    @Override
    public void delete(JobTitle object) {
        entityManager.remove(object);
    }

    @Override
    public boolean isContained(JobTitle object) {
        return findExisted(object) != null;
    }
    
    public boolean isContained(String title) {
        return findExisted(title) != null;
    }

    @Override
    public JobTitle createAndGet(JobTitle object) {
      	entityManager.persist(object);
      	entityManager.flush();
      	return object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JobTitle findExisted(JobTitle object) {
      	Query query = entityManager.createQuery("select j from JobTitle j " + 
                "WHERE j.title = :p");
      	query.setParameter("p", object.getTitle());
      	return (JobTitle) query.getResultList().stream()
                .findFirst()
                .orElse(null);
    }
    
    @SuppressWarnings("unchecked")
    public JobTitle findExisted(String title) {
      	Query query = entityManager.createQuery("select j from JobTitle j " + 
                "WHERE j.title = :p");
      	query.setParameter("p", title);
      	return (JobTitle) query.getResultList().stream()
                .findFirst()
                .orElse(null);
    }
}
