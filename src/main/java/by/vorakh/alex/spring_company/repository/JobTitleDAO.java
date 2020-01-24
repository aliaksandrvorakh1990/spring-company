package by.vorakh.alex.spring_company.repository;

import java.util.List;

import javax.persistence.EntityExistsException;
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
	return (List<JobTitle>) entityManager.createQuery("select j from JobTitle j").getResultList();
    }

    @Override
    public JobTitle getById(int id) {
	return entityManager.find(JobTitle.class, id);
    }

    @SuppressWarnings("finally")
    @Override
    public int create(JobTitle object) {
	int createdID = -1;
	try {
        	entityManager.persist(object);
        	entityManager.flush();
        	createdID = object.getId();
	} catch (EntityExistsException e) {
	    throw new DAOException("The \'" + object.getTitle() + 
		    "\' cannot be created, because to exist in database.", e);
	} catch (IllegalArgumentException ex) {
	    throw new DAOException("The JOBTITLE cannot be created, because " + 
		    object.toString() +  " is not a JOBTITLE object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new DAOException("The \'" + object.getTitle() + 
		    "\' cannot be created, NO Transaction.", exc);
	} catch (javax.persistence.PersistenceException ex1) { 
	    throw new DAOException("The \'" + object.getTitle() + 
		    "\' cannot be created, the database is not updated.", ex1);
	} finally {
	    return createdID;
	}
    }

    @Override
    public void update(JobTitle object) {
	try {
	    entityManager.merge(object);
	} catch (IllegalArgumentException ex) {
	    throw new DAOException("The JOBTITLE cannot be updated, because " + 
		    object.toString() +  " is not a JOBTITLE object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new DAOException("The \'" + object.getTitle() + 
		    "\' cannot be updated, NO Transaction.", exc);
	}
    }

    @Override
    public void delete(JobTitle object) {
	try {
	    entityManager.remove(object);
	} catch (IllegalArgumentException ex) {
	    throw new DAOException("The JOBTITLE cannot be deleted, because " + 
		    object.toString() +  " is not a JOBTITLE object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new DAOException("The \'" + object.getTitle() + 
		    "\' cannot be deleted, NO Transaction.", exc);
	}
	
    }

    @Override
    public boolean isContained(JobTitle object) {
	if (findExisted(object) == null) {
	    return false;
	}
	return true;
    }

    @SuppressWarnings("finally")
    @Override
    public JobTitle createAndGet(JobTitle object) {
	JobTitle newJobTitle = null;
	try {
    	    entityManager.persist(object);
    	    entityManager.flush();
    	    newJobTitle = object;
	} catch (EntityExistsException e) {
	    throw new DAOException("The \'" + object.getTitle() + 
		    "\' cannot be created, because to exist in database.", e);
	} catch (IllegalArgumentException ex) {
	    throw new DAOException("The JOBTITLE cannot be created, because " + 
		    object.toString() +  " is not a JOBTITLE object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new DAOException("The \'" + object.getTitle() + 
		    "\' cannot be created, NO Transaction.", exc);
	} catch (javax.persistence.PersistenceException ex1) { 
	    throw new DAOException("The \'" + object.getTitle() + 
		    "\' cannot be created and got, the database is not updated.", ex1);
	} finally {
	    return newJobTitle;
	}
    }

    @SuppressWarnings("unchecked")
    @Override
    public JobTitle findExisted(JobTitle object) {
	Query query = entityManager.createQuery("select j from JobTitle j "
	 	+ "WHERE j.title = :p");
	query.setParameter("p", object.getTitle());
	return (JobTitle) query.getResultList().stream().findFirst().orElse(null);
    }

}
