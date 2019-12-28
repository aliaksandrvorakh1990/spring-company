package by.vorakh.alex.spring_company.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.repository.SkillDAO;
import by.vorakh.alex.spring_company.repository.entity.Skill;

@Service
public class SkillService implements ServiceInterface<Skill> {

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
    public void create(Skill object) {
	skillDAO.create(object);
    }

    @Override
    @Transactional
    public void update(Skill object) {
	skillDAO.create(object);
    }
    
    @Override
    public void update(int id, Skill editedObject) {
	// TODO Auto-generated method stub
	
    }

    @Override
    @Transactional
    public void delete(int id) {
	Skill deletedSkill = skillDAO.getById(id);
	skillDAO.create(deletedSkill);
    }

    

}
