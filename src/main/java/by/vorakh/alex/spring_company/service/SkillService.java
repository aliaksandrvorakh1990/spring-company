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
import by.vorakh.alex.spring_company.repository.SkillDAO;
import by.vorakh.alex.spring_company.repository.entity.Skill;

@Service
public class SkillService implements ServiceInterface<SkillViewModel, SkillPayload> {

    @Autowired
    private SkillDAO skillDAO;
    @Autowired
    private EmployeeService employeeService;
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
    
    
    public List<Skill> getListBy(List<Integer> skillIdList) {
	List<Skill> list = new ArrayList<Skill>();
	for (Integer skillId : skillIdList) {
	    list.add(skillDAO.getById(skillId)) ;
	}
	return list;
    }
    

    @SuppressWarnings("finally")
    @Override
    @Transactional
    public IdViewModel create(SkillPayload newPayload) {
	int createdID = -1;
	if (skillDAO.isContained(newPayload.getSkillName())) {
	    throw new ServiceException("The \"" + newPayload.getSkillName() + 
		    "\" cannot be created, because to exist in database.");
	}
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
    
    @Transactional
    public SkillViewModel getOrCreateAndGet(SkillOutsource extemalSource) {
	Skill newSkill = extermalSourceConvertor.convert(extemalSource);
	return getOrCreateAndGet(newSkill);
    }
    
    
    @Transactional
    public SkillViewModel getOrCreateAndGet(Skill newSkill) {
	return convertor.convert(getOrCreateAndGetWithId(newSkill));
    }
    
    @SuppressWarnings("finally")
    @Transactional
    public Skill getOrCreateAndGetWithId(Skill newSkill) {
	if (skillDAO.isContained(newSkill.getSkillName())) {
	    return skillDAO.findExisted(newSkill.getSkillName());
	} else {
	    Skill returnedSkill = null;
	    try {
		returnedSkill = skillDAO.createAndGet(newSkill);
	    } catch (EntityExistsException e) {
		throw new ServiceException("The \"" + newSkill.getSkillName() + 
			"\" cannot be created, because to exist in database.", e);
	    } catch (IllegalArgumentException ex) {
		    throw new ServiceException("The SKILL cannot be created, because " + 
			    newSkill.toString() +  " is not a Skill object.", ex);
	    }  catch (javax.persistence.TransactionRequiredException exc) { 
		    throw new ServiceException("The \"" + newSkill.getSkillName() + 
			    "\" cannot be created, NO Transaction.", exc);
	    } catch (javax.persistence.PersistenceException ex1) { 
		throw new ServiceException("The \"" + newSkill.getSkillName() + 
			"\" cannot be created, the database is not updated.", ex1);
	    } finally {
		return returnedSkill;
	    }
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
	
	employeeService.removeDeletedSkillfromSkillLists(deletedSkill);
	
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
}
