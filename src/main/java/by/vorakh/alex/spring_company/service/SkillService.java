package by.vorakh.alex.spring_company.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.model.SkillPayload;
import by.vorakh.alex.spring_company.repository.SkillDAO;
import by.vorakh.alex.spring_company.repository.entity.Skill;

@Service
public class SkillService implements ServiceInterface<Skill, SkillPayload> {

    @Autowired
    private SkillDAO skillDAO;
    
    @Override
    public List<Skill> getAll() {
	return skillDAO.getAll();
    }

    @Override
    public Skill getById(int id) {
	return skillDAO.getById(id);
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
	skillDAO.delete(deletedSkill);
    }

}
