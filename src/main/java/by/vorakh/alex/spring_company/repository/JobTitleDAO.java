package by.vorakh.alex.spring_company.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import by.vorakh.alex.spring_company.repository.entity.JobTitle;

@Repository
public class JobTitleDAO implements DAO<JobTitle> {
    
    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<JobTitle> getAll() {
	return (List<JobTitle>) entityManager.createQuery("select c from JobTitle c").getResultList();
    }

    @Override
    public JobTitle getById(int id) {
	return entityManager.find(JobTitle.class, id);
    }

    @Override
    public void create(JobTitle object) {
	entityManager.persist(object);
    }

    @Override
    public void update(JobTitle object) {
	entityManager.merge(object);
    }

    @Override
    public void delete(JobTitle object) {
	entityManager.remove(object);
    }

}
