package by.vorakh.alex.spring_company.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.converter.SkillOutsourceToSkillConverter;
import by.vorakh.alex.spring_company.converter.SkillToSkillViewModelConverter;
import by.vorakh.alex.spring_company.model.external.ExternalSkill;
import by.vorakh.alex.spring_company.model.payload.SkillPayload;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.model.view_model.SkillViewModel;
import by.vorakh.alex.spring_company.repository.SkillDAO;
import by.vorakh.alex.spring_company.repository.entity.Skill;

@Service
public class SkillService implements 
        ServiceInterface<SkillViewModel, SkillPayload> {
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
        List<SkillViewModel> skillViewModelList = new ArrayList<>();
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
        List<Skill> list = new ArrayList<>();
        for (Integer skillId : skillIdList) {
            list.add(skillDAO.getById(skillId)) ;
        }
        return list;
    }
    
    @Override
    @Transactional
    public IdViewModel create(SkillPayload newPayload) {
        int createdID;
        Skill createdSkill;
        String skillName = newPayload.getSkillName();
        if (skillDAO.isContained(skillName)) {
            throw new ServiceException("The \"" + skillName
                    + "\" cannot be created, because to exist in database.");
        }
        createdSkill = new Skill(skillName);
        
        try {
            createdID = skillDAO.create(createdSkill);
            return new IdViewModel(createdID);
        } catch (EntityExistsException e) {
            throw new ServiceException("The \"" + skillName + "\" cannot " 
                    + "be created, because to exist in database.", e);
        } catch (IllegalArgumentException ex) {
            throw new ServiceException("The SKILL cannot be created, because " 
                    + createdSkill +" is not a Skill object.", ex);
        }  catch (javax.persistence.TransactionRequiredException exc) { 
            throw new ServiceException("The \"" + skillName +  "\" cannot " 
                    + "be created, NO Transaction.", exc);
        } catch (javax.persistence.PersistenceException ex1) { 
            throw new ServiceException("The \"" + skillName + "\" cannot " 
                    + "be created, the database is not updated.", ex1);
        } 
    }
    
    @Transactional
    public SkillViewModel getOrCreateAndGet(ExternalSkill extemalSource) {
      	Skill newSkill = extermalSourceConvertor.convert(extemalSource);
      	return getOrCreateAndGet(newSkill);
    }
    
    @Transactional
    public SkillViewModel getOrCreateAndGet(Skill newSkill) {
        return convertor.convert(getOrCreateAndGetWithId(newSkill));
    }
    
    @Transactional
    public Skill getOrCreateAndGetWithId(Skill newSkill) {
      	String skillName = newSkill.getName();
      	if (skillDAO.isContained(skillName)) {
      	    return skillDAO.findExisted(skillName);
      	} else {
      	    Skill returnedSkill;
      	    try {
            		returnedSkill = skillDAO.createAndGet(newSkill);
            		return returnedSkill;
      	    } catch (EntityExistsException e) {
      		      throw new ServiceException("The \"" + skillName + "\" cannot " 
                        + "be created, because to exist in database.", e);
      	    } catch (IllegalArgumentException ex) {
        		    throw new ServiceException("The SKILL cannot be created, " 
                        + "because " + newSkill +  " is not a Skill.", ex);
      	    }  catch (javax.persistence.TransactionRequiredException exc) { 
        		    throw new ServiceException("The \"" + skillName + "\" cannot " 
                        + "be created, NO Transaction.", exc);
      	    } catch (javax.persistence.PersistenceException ex1) { 
      		      throw new ServiceException("The \"" + skillName + "\" cannot " 
                        + "be created, the database is not updated.", ex1);
      	    } 
      	}
    }
    
    @Override
    @Transactional
    public void update(SkillPayload editedPayload) {
        String skillName = editedPayload.getSkillName();
        int id = editedPayload.getId();
      	if (skillDAO.isContained(editedPayload.getSkillName())) {
      	    throw new ServiceException("The \"" + skillName +  "\" cannot " 
                    + "be updated, because to exist in database");
      	}
      	
      	Skill editedSkill = skillDAO.getById(id);
      	
      	if (editedSkill == null) {
      	    throw new ServiceException("The \"" + skillName +  "\" cannot " 
                    + "be updated, because the skill with \'" + id 
      		          + "\' ID does not exist in database");
      	}
      	
      	editedSkill.setName(editedPayload.getSkillName());
      	
      	try {
      	    skillDAO.update(editedSkill);
      	} catch (IllegalArgumentException ex) {
      	    throw new ServiceException("The SKILL cannot be updated, because " 
                    + editedSkill +  " is not a Skill object.", ex);
      	} catch (javax.persistence.TransactionRequiredException exc) { 
      	    throw new ServiceException("The \"" + skillName + "\" cannot " 
                    + "be updated, NO Transaction.", exc);
      	}
    }

    @Override
    @Transactional
    public void delete(int id) {
      	Skill deletedSkill = skillDAO.getById(id);
      	if (deletedSkill == null) {
      	    throw new ServiceException("The skill with \'" + id+ "\' ID cannot " 
                    + "be deleted, because to no exist in database");
      	}
      	
      	employeeService.removeDeletedSkillfromSkillLists(deletedSkill);
      	
      	try {
      	   skillDAO.delete(deletedSkill);
      	} catch (IllegalArgumentException ex) {
      	    throw new ServiceException("The SKILL cannot be deleted, because " 
                    +   deletedSkill +  " is not a Skill object.", ex);
      	}  catch (javax.persistence.TransactionRequiredException exc) { 
      	    throw new ServiceException("The \"" + deletedSkill + "\" cannot " 
                    + "be deleted, NO Transaction.", exc);
      	}
    }
}
