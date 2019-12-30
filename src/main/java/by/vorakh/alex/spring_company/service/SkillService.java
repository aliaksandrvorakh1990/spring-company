package by.vorakh.alex.spring_company.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.model.NewPayloadEntityInterface;
import by.vorakh.alex.spring_company.model.SkillPayload;
import by.vorakh.alex.spring_company.model.UpdatePyaloadEntityInterface;
import by.vorakh.alex.spring_company.repository.SkillDAO;
import by.vorakh.alex.spring_company.repository.entity.Skill;

@Service
public class SkillService implements ServiceInterface<Skill, SkillPayload> {

    @Autowired
    private SkillDAO skillDAO;
    
    private NewPayloadEntityInterface<Skill, SkillPayload> newSkillEntity = (payload) -> {
	return new Skill(payload);
    };
    
    private UpdatePyaloadEntityInterface<Skill, SkillPayload> editedSkillEntity = (id, payload) -> {
	Skill editedSkill = skillDAO.getById(id);
	editedSkill.setSkillName(payload);
	return editedSkill;
    };
    
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
	skillDAO.create(newSkillEntity.build(newPayload));
    }

    @Override
    @Transactional
    public void update(int id, SkillPayload editedPayload) {
	skillDAO.update(editedSkillEntity.edit(id, editedPayload));
    }

    @Override
    @Transactional
    public void delete(int id) {
	Skill deletedSkill = skillDAO.getById(id);
	skillDAO.create(deletedSkill);
    }

}
