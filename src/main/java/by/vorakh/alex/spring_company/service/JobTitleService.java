package by.vorakh.alex.spring_company.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.converter.JobTitleOutsourceToJobTitleConverter;
import by.vorakh.alex.spring_company.converter.JobTitleToJobTitleViewModelConverter;
import by.vorakh.alex.spring_company.model.outsource.JobTitleOutsource;
import by.vorakh.alex.spring_company.model.payload.JobTitlePayload;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.model.view_model.JobTitleViewModel;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.JobTitleDAO;
import by.vorakh.alex.spring_company.repository.entity.JobTitle;


@Service
public class JobTitleService implements ServiceInterface<JobTitleViewModel, JobTitlePayload> {

    @Autowired
    private JobTitleDAO jobTitleDAO;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private JobTitleToJobTitleViewModelConverter convertor;
    @Autowired
    private JobTitleOutsourceToJobTitleConverter extermalSourceConvertor;
        
    @Override
    public List<JobTitleViewModel> getAll() {
	List<JobTitleViewModel> jobTitleViewModelList = new ArrayList<JobTitleViewModel>();
	jobTitleDAO.getAll().forEach(jobTitle -> {
	    jobTitleViewModelList.add(convertor.convert(jobTitle));
	});
	return jobTitleViewModelList;
    }

    @Override
    public JobTitleViewModel getById(int id) {
	return convertor.convert(jobTitleDAO.getById(id));
    }

    @SuppressWarnings("finally")
    @Override
    @Transactional
    public IdViewModel create(JobTitlePayload newPayload) {
	int createdID = -1;
	JobTitle createdJobTitle = new JobTitle(newPayload.getTitle());
	
	try {
	    createdID = jobTitleDAO.create(createdJobTitle);
	} catch (EntityExistsException e) {
	    throw new ServiceException("The \"" + createdJobTitle.getTitle() + 
		    "\" cannot be created, because to exist in database.", e);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The JOBTITLE cannot be created, because " + 
		    createdJobTitle.toString() +  " is not a JOBTITLE object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + createdJobTitle.getTitle() + 
		    "\" cannot be created, NO Transaction.", exc);
	} catch (javax.persistence.PersistenceException ex1) { 
	    throw new ServiceException("The \"" + createdJobTitle.getTitle() + 
		    "\" cannot be created, the database is not updated.", ex1);
	} finally {
	    return new IdViewModel(createdID);
	}
    }
    
    @Transactional
    public JobTitleViewModel getOrCreateAndGet(JobTitleOutsource extemalSource) {
	JobTitle newJobTitle = extermalSourceConvertor.convert(extemalSource);
	return getOrCreateAndGet(newJobTitle);
    }

    @SuppressWarnings("finally")
    @Transactional
    public JobTitleViewModel getOrCreateAndGet(JobTitle newJobTitle) {
	if (jobTitleDAO.isContained(newJobTitle)) {
	    return convertor.convert(jobTitleDAO.findExisted(newJobTitle));
	} else {
	    JobTitle returnedJobTitle = null;
	    try { 
		returnedJobTitle = jobTitleDAO.createAndGet(newJobTitle);
	    } catch (EntityExistsException e) {
		throw new ServiceException("The \"" + newJobTitle.getTitle() + 
			"\" cannot be created, because to exist in database.", e);
	    } catch (IllegalArgumentException ex) {
		    throw new ServiceException("The SKILL cannot be created, because " + 
			    newJobTitle.toString() +  " is not a Skill object.", ex);
	    }  catch (javax.persistence.TransactionRequiredException exc) { 
		    throw new ServiceException("The \"" + newJobTitle.getTitle() + 
			    "\" cannot be created, NO Transaction.", exc);
	    } catch (javax.persistence.PersistenceException ex1) { 
		throw new ServiceException("The \"" + newJobTitle.getTitle() + 
			"\" cannot be created, the database is not updated.", ex1);
	    } finally {
		return convertor.convert(returnedJobTitle);
	    }
	}
    }
    
    @Override
    @Transactional
    public void update(JobTitlePayload editedPayload) {
	if (jobTitleDAO.isContained(editedPayload.getTitle())) {
	    throw new ServiceException("The \"" + editedPayload.getTitle() + 
		    "\" cannot be updated, because to exist in database");
	}
	
	JobTitle editedJobTitle = jobTitleDAO.getById(editedPayload.getId());
	
	if (editedJobTitle == null) {
	    throw new ServiceException("The \"" + editedPayload.getTitle() + 
		    "\" cannot be updated, because the skill with \'" + editedPayload.getId() 
		    + "\' ID does not exist in database");
	}
	
	editedJobTitle.setTitle(editedPayload.getTitle());
	try {
	    jobTitleDAO.update(editedJobTitle);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The JOBTILE cannot be updated, because " + 
		    editedJobTitle.toString() +  " is not a Skill object.", ex);
	} catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + editedJobTitle.getTitle() + 
		    "\" cannot be updated, NO Transaction.", exc);
	}
	
    }
   
    @Override
    @Transactional
    public void delete(int id) {
	JobTitle deletedJobTitle = jobTitleDAO.getById(id);
	
	if (deletedJobTitle == null) {
	    throw new ServiceException("The JOBTITLE with \'" + id  + 
		    "\' ID cannot be deleted, because to no exist in database");
	}
	
	try {
	    employeeDAO.delete(deletedJobTitle);
	    jobTitleDAO.delete(deletedJobTitle);
	}  catch (IllegalArgumentException ex) {
	    throw new ServiceException("The JOBTITLE cannot be deleted, because " + 
		    deletedJobTitle.toString() +  " is not a JOBTITLE object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \'" + deletedJobTitle.getTitle() + 
		    "\' cannot be deleted, NO Transaction.", exc);
	}
    }

}
