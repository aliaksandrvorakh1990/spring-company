package by.vorakh.alex.spring_company.repository;

import by.vorakh.alex.spring_company.repository.entity.Company;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

@Repository
public class CompanyDAO implements DAO<Company> {
    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Company> getAll() {
        return entityManager.createQuery("select c from Company c").getResultList();
    }

    @Override
    public Company getById(int id) {
        return entityManager.find(Company.class, id);
    }

    @Override
    public int create(Company object) {
        entityManager.persist(object);
        entityManager.flush();
	return object.getId();
    }

    @Override
    public void update(Company object) {
	entityManager.merge(object);
    }

    @Override
    public void delete(Company object) {
        entityManager.remove(object);
    }

    @Override
    public boolean isContained(Company object) {
	return findExisted(object) != null;
    }
    
    public boolean isContained(String companyName) {
	return findExisted(companyName) != null;
    }

    @Override
    public Company createAndGet(Company object) {
	entityManager.persist(object);
	entityManager.flush();
	return object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Company findExisted(Company object) {
	Query query = entityManager.createQuery("select c from Company c "
	 	+ "WHERE c.name = :p");
	query.setParameter("p", object.getName());
	return (Company) query.getResultList().stream().findFirst().orElse(null);
    }
    
    @SuppressWarnings("unchecked")
    public Company findExisted(String companyName) {
	Query query = entityManager.createQuery("select c from Company c "
	 	+ "WHERE c.name = :p");
	query.setParameter("p", companyName);
	return (Company) query.getResultList().stream().findFirst().orElse(null);
    }
}
