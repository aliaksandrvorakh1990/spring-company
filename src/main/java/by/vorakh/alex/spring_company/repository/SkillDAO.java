package by.vorakh.alex.spring_company.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    public List<Skill> getAll(List<Integer> skillIdList) {
	List<Skill> list = new ArrayList<Skill>();
	
	for (Integer skillId : skillIdList) {
	    list.add(entityManager.find(Skill.class, skillId));
	}
	return list;
    }

    @Override
    public Skill getById(int id) {
	return entityManager.find(Skill.class, id);
    }

    @Override
    public int create(Skill object) {
	entityManager.persist(object);
	entityManager.flush();
	
	return object.getId();
    }

    @Override
    public void update(Skill object) {
	entityManager.merge(object);
    }

    @Override
    public void delete(Skill object) {
	entityManager.remove(object);
    }

    @Override
    public boolean isContained(Skill object) {
	if (findExisted(object) == null) {
	    return false;
	}
	return true;
    }

    @Override
    public Skill createAndGet(Skill object) {
	entityManager.persist(object);
	entityManager.flush();
	return object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Skill findExisted(Skill object) {
	Query query = entityManager.createQuery("select s from Skill s "
	 	+ "WHERE s.skillName = :p");
	query.setParameter("p", object.getSkillName());
	return (Skill) query.getResultList().stream().findFirst().orElse(null);
	
    }

}
