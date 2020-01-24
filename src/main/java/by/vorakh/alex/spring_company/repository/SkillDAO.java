package by.vorakh.alex.spring_company.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
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
	return (List<Skill>) entityManager.createQuery("select s from Skill s").getResultList();
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

    @SuppressWarnings("finally")
    @Override
    public int create(Skill object) {
	int createdID = -1;
	try {
	    entityManager.persist(object);
	    entityManager.flush();
	    createdID = object.getId();
	} catch (EntityExistsException e) {
	    throw new DAOException("The \"" + object.getSkillName() + 
		    "\" cannot be created, because to exist in database.", e);
	} catch (IllegalArgumentException ex) {
	    throw new DAOException("The SKILL cannot be created, because " + 
		    object.toString() +  " is not a Skill object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new DAOException("The \"" + object.getSkillName() + 
		    "\" cannot be created, NO Transaction.", exc);
	} catch (javax.persistence.PersistenceException ex1) { 
	    throw new DAOException("The \"" + object.getSkillName() + 
		    "\" cannot be created, the database is not updated.", ex1);
	} finally {
	    return createdID;
	}
	
    }

    @Override
    public void update(Skill object) {
	try {
	    entityManager.merge(object);
	} catch (IllegalArgumentException ex) {
	    throw new DAOException("The SKILL cannot be updated, because " + 
		    	object.toString() +  " is not a Skill object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new DAOException("The \"" + object.getSkillName() + 
		    "\" cannot be updated, NO Transaction.", exc);
	}
    }

    @Override
    public void delete(Skill object) {
	try {
	    entityManager.remove(object);
	} catch (IllegalArgumentException ex) {
	    throw new DAOException("The SKILL cannot be deleted, because " + 
		    	object.toString() +  " is not a Skill object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new DAOException("The \"" + object.getSkillName() + 
		    "\" cannot be deleted, NO Transaction.", exc);
	}
    }

    @Override
    public boolean isContained(Skill object) {
	if (findExisted(object) == null) {
	    return false;
	}
	return true;
    }
    
    public boolean isContained(String skillName) {
	
	if (findExisted(skillName) == null) {
	    return false;
	}
	return true;
    }

    @SuppressWarnings("finally")
    @Override
    public Skill createAndGet(Skill object) {
	Skill newSkill = null;
	try {
    	    entityManager.persist(object);
    	    entityManager.flush();
    	    newSkill = object;
	} catch (EntityExistsException e) {
	    throw new DAOException("The \"" + object.getSkillName() + 
		    "\" cannot be created, because to exist in database.", e);
	} catch (IllegalArgumentException ex) {
	    throw new DAOException("The SKILL cannot be created, because " + 
		    object.toString() +  " is not a Skill object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new DAOException("The \"" + object.getSkillName() + 
		    "\" cannot be created, NO Transaction.", exc);
	} catch (javax.persistence.PersistenceException ex1) { 
	    throw new DAOException("The \"" + object.getSkillName() + 
		    "\" cannot be created, the database is not updated.", ex1);
	} finally {
	    return newSkill;
	}
	
    }

    @SuppressWarnings("unchecked")
    @Override
    public Skill findExisted(Skill object) {
	Query query = entityManager.createQuery("select s from Skill s "
	 	+ "WHERE s.skillName = :p");
	query.setParameter("p", object.getSkillName());
	return (Skill) query.getResultList().stream().findFirst().orElse(null);
	
    }
    
    @SuppressWarnings("unchecked")
    public Skill findExisted(String skillName) {
	Query query = entityManager.createQuery("select s from Skill s "
	 	+ "WHERE s.skillName = :p");
	query.setParameter("p", skillName);
	return (Skill) query.getResultList().stream().findFirst().orElse(null);
    }
    
}
