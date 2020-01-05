package by.vorakh.alex.spring_company.repository;

import java.util.ArrayList;
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

}
