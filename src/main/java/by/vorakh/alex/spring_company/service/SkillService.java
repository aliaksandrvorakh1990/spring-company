package by.vorakh.alex.spring_company.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.converter.SkillOutsourceToSkillConverter;
import by.vorakh.alex.spring_company.converter.SkillToSkillViewModelConverter;
import by.vorakh.alex.spring_company.model.outsource.SkillOutsource;
import by.vorakh.alex.spring_company.model.payload.SkillPayload;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.model.view_model.SkillViewModel;
import by.vorakh.alex.spring_company.repository.DAOException;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.SkillDAO;
import by.vorakh.alex.spring_company.repository.entity.Skill;

@Service
public class SkillService implements ServiceInterface<SkillViewModel, SkillPayload> {

    @Autowired
    private SkillDAO skillDAO;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private SkillToSkillViewModelConverter convertor;
    @Autowired
    private SkillOutsourceToSkillConverter extermalSourceConvertor;
    
    @Override
    public List<SkillViewModel> getAll() {
	List<SkillViewModel> skillViewModelList = new ArrayList<SkillViewModel>();
	skillDAO.getAll().forEach(skill -> {
	    skillViewModelList.add(convertor.convert(skill));
	});
	return skillViewModelList;
    }

    @Override
    public SkillViewModel getById(int id) {
	return convertor.convert(skillDAO.getById(id));
    }

    @SuppressWarnings("finally")
    @Override
    @Transactional
    public IdViewModel create(SkillPayload newPayload) {
	int createdID = -1;
	Skill createdSkill = new Skill(newPayload.getSkillName());
	
	try {
	    createdID = skillDAO.create(createdSkill);
	} catch (EntityExistsException e) {
	    throw new ServiceException("The \"" + createdSkill.getSkillName() + 
		    "\" cannot be created, because to exist in database.", e);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The SKILL cannot be created, because " + 
		    createdSkill.toString() +  " is not a Skill object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + createdSkill.getSkillName() + 
		    "\" cannot be created, NO Transaction.", exc);
	} catch (javax.persistence.PersistenceException ex1) { 
	    throw new ServiceException("The \"" + createdSkill.getSkillName() + 
		    "\" cannot be created, the database is not updated.", ex1);
	} finally {
	    return new IdViewModel(createdID);
	}
    }

    @Override
    @Transactional
    public void update(SkillPayload editedPayload) {
	if (skillDAO.isContained(editedPayload.getSkillName())) {
	    throw new ServiceException("The \"" + editedPayload.getSkillName() + 
		    "\" cannot be updated, because to exist in database");
	}
	
	Skill editedSkill = skillDAO.getById(editedPayload.getId());
	
	if (editedSkill == null) {
	    throw new ServiceException("The \"" + editedPayload.getSkillName() + 
		    "\" cannot be updated, because the skill with \'" + editedPayload.getId() 
		    + "\' ID does not exist in database");
	}
	
	editedSkill.setSkillName(editedPayload.getSkillName());
	
	try {
	    skillDAO.update(editedSkill);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The SKILL cannot be updated, because " + 
		    editedSkill.toString() +  " is not a Skill object.", ex);
	} catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + editedSkill.getSkillName() + 
		    "\" cannot be updated, NO Transaction.", exc);
	}
	
    }

    @Override
    @Transactional
    public void delete(int id) {
	Skill deletedSkill = skillDAO.getById(id);
	if (deletedSkill == null) {
	    throw new ServiceException("The skill with \'" + id  + 
		    "\' ID cannot be deleted, because to no exist in database");
	}
	employeeDAO.getAll(deletedSkill).forEach(emp -> {
	    emp.getSkillList().remove(deletedSkill);
	    employeeDAO.update(emp);
	});
	try {
	skillDAO.delete(deletedSkill);
	} catch (IllegalArgumentException ex) {
	    throw new ServiceException("The SKILL cannot be deleted, because " + 
		    deletedSkill.toString() +  " is not a Skill object.", ex);
	}  catch (javax.persistence.TransactionRequiredException exc) { 
	    throw new ServiceException("The \"" + deletedSkill.getSkillName() + 
		    "\" cannot be deleted, NO Transaction.", exc);
	}
    }
    
    @Transactional
    public Skill createAndGet(SkillOutsource extemalSource) {
	Skill newSkill = extermalSourceConvertor.convert(extemalSource);
	return createAndGet(newSkill);
    }
    
    @SuppressWarnings("finally")
    @Transactional
    public Skill createAndGet(Skill newSkill) {
	if (skillDAO.isContained(newSkill)) {
	    return skillDAO.findExisted(newSkill);
	} else {
	    Skill returnedSkill = null;
	    try {
		returnedSkill = skillDAO.createAndGet(newSkill);
	    } catch (EntityExistsException e) {
		throw new DAOException("The \"" + newSkill.getSkillName() + 
			"\" cannot be created, because to exist in database.", e);
	    } catch (IllegalArgumentException ex) {
		    throw new DAOException("The SKILL cannot be created, because " + 
			    newSkill.toString() +  " is not a Skill object.", ex);
	    }  catch (javax.persistence.TransactionRequiredException exc) { 
		    throw new DAOException("The \"" + newSkill.getSkillName() + 
			    "\" cannot be created, NO Transaction.", exc);
	    } catch (javax.persistence.PersistenceException ex1) { 
		throw new DAOException("The \"" + newSkill.getSkillName() + 
			"\" cannot be created, the database is not updated.", ex1);
	    } finally {
		return returnedSkill;
	    }
	}
    }

}
