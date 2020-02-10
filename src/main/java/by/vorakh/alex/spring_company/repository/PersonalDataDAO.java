package by.vorakh.alex.spring_company.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import by.vorakh.alex.spring_company.repository.entity.PersonalData;

@Repository
public class PersonalDataDAO implements DAO<PersonalData> {
    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<PersonalData> getAll() {
	return entityManager.createQuery("select p from PersonalData p").getResultList();
    }

    @Override
    public PersonalData getById(int id) {
	return entityManager.find(PersonalData.class, id);
    }

    @Override
    public int create(PersonalData object) {
	entityManager.persist(object);
	entityManager.flush();
	return object.getId();
    }

    @Override
    public void update(PersonalData object) {
	entityManager.merge(object);
    }

    @Override
    public void delete(PersonalData object) {
	entityManager.remove(object);
    }

    @Override
    public boolean isContained(PersonalData object) {
	return false;
    }

    @Override
    public PersonalData createAndGet(PersonalData object) {
	entityManager.persist(object);
	entityManager.flush();
	return object;
    }

    @Override
    public PersonalData findExisted(PersonalData object) {
	return null;
    }
}
