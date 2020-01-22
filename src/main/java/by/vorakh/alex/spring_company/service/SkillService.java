package by.vorakh.alex.spring_company.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.converter.SkillOutsourceToSkillConverter;
import by.vorakh.alex.spring_company.converter.SkillToSkillViewModelConverter;
import by.vorakh.alex.spring_company.model.outsource.SkillOutsource;
import by.vorakh.alex.spring_company.model.payload.SkillPayload;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.model.view_model.SkillViewModel;
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
	//TODO 404 if return null
	return convertor.convert(skillDAO.getById(id));
    }

    @Override
    @Transactional
    public IdViewModel create(SkillPayload newPayload) {
	Skill createdSkill = new Skill(newPayload.getSkillName());
	//TODO 201 if isContained - true
	int id = skillDAO.create(createdSkill);
	return new IdViewModel(id);
    }

    @Override
    @Transactional
    public void update(SkillPayload editedPayload) {
	Skill editedSkill = skillDAO.getById(editedPayload.getId());
	//TODO 404 if return null
	editedSkill.setSkillName(editedPayload.getSkillName());
	//TODO 201 if isContained - true (checking if skill exist in DB)
	skillDAO.update(editedSkill);
    }

    @Override
    @Transactional
    public void delete(int id) {
	Skill deletedSkill = skillDAO.getById(id);
	//TODO HOW IMPROVE maybe need to do native SQL query?
	employeeDAO.getAll(deletedSkill).forEach(emp -> {
	    emp.getSkillList().remove(deletedSkill);
	    employeeDAO.update(emp);
	});
	skillDAO.delete(deletedSkill);
    }
    
    @Transactional
    public Skill createAndGet(SkillOutsource extemalSource) {
	Skill newSkill =extermalSourceConvertor.convert(extemalSource);
	return createAndGet(newSkill);
    }
    
    @Transactional
    public Skill createAndGet(Skill newSkill) {
	//TODO Chech _- IS THERE A GOOD SOLUTION?
	if (skillDAO.isContained(newSkill)) {
	    return   skillDAO.findExisted(newSkill);
	} else {
	    return skillDAO.createAndGet(newSkill);
	}
    }

}
