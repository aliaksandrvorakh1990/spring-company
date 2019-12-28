package by.vorakh.alex.spring_company.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import by.vorakh.alex.spring_company.repository.entity.Skill;

@Repository
public class SkillDAO implements DAO<Skill> {
    
    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Skill> getAll() {
	return (List<Skill>) entityManager.createQuery("select c from Skill c").getResultList();
    }

    @Override
    public Skill getById(int id) {
	return entityManager.find(Skill.class, id);
    }

    @Override
    public void create(Skill object) {
	entityManager.persist(object);
    }

    @Override
    public void update(Skill object) {
	entityManager.merge(object);
    }

    @Override
    public void delete(Skill object) {
	entityManager.remove(object);
    }

}
