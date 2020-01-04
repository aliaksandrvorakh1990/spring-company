package by.vorakh.alex.spring_company.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.converter.EntityToViewModelConverter;
import by.vorakh.alex.spring_company.model.payload.SkillPayload;
import by.vorakh.alex.spring_company.model.view_model.CompanyViewModel;
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
    private EntityToViewModelConverter convertor;
    
    @Override
    public List<SkillViewModel> getAll() {
	List<SkillViewModel> skillViewModelList = new ArrayList<SkillViewModel>();
	skillDAO.getAll().forEach(skill -> {
	    skillViewModelList.add(convertor.converte(skill));
	});
	return skillViewModelList;
    }

    @Override
    public SkillViewModel getById(int id) {
	return convertor.converte(skillDAO.getById(id));
    }

    @Override
    @Transactional
    public void create(SkillPayload newPayload) {
	skillDAO.create(new Skill(newPayload.getSkillName()));
    }

    @Override
    @Transactional
    public void update(int id, SkillPayload editedPayload) {
	Skill editedSkill = skillDAO.getById(id);
	editedSkill.setSkillName(editedPayload.getSkillName());
	skillDAO.update(editedSkill);
    }

    @Override
    @Transactional
    public void delete(int id) {
	Skill deletedSkill = skillDAO.getById(id);
	employeeDAO.getAll(deletedSkill).forEach(emp -> {
	    emp.getSkillList().remove(deletedSkill);
	    employeeDAO.update(emp);
	});
	skillDAO.delete(deletedSkill);
    }

}
