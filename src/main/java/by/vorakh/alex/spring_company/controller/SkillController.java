package by.vorakh.alex.spring_company.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.vorakh.alex.spring_company.repository.entity.Skill;
import by.vorakh.alex.spring_company.service.SkillService;

@RestController
@RequestMapping("/project")
public class SkillController {
    
    @Autowired
    private SkillService skillService;
    
    @GetMapping("/skills")
    public List<Skill> getSkills() {
        return skillService.getAll();
    }

    @GetMapping(value = "/skills/{id}")
    public Skill getSkill(@PathVariable("id") Integer id) {
        return skillService.getById(id);
    }

    @PostMapping("/skills")
    public void createSkill(@Valid @RequestBody Skill newSkill) {
	skillService.create(newSkill);
    }

    @PutMapping(value = "/skills/{id}")
    public void updateSkill(@PathVariable(value = "id") Integer id,
                              @Valid @RequestBody Skill editedSkill) {
	Skill skill = skillService.getById(id);

        skill.setSkillName(editedSkill.getSkillName());
        
        skillService.update(skill);
    }

    @DeleteMapping(value = "/skills/{id}")
    public void deleteSkill(@PathVariable("id") Integer id) {
	skillService.delete(id);
    }

}