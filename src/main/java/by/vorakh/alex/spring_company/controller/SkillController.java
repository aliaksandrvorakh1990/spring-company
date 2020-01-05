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

import by.vorakh.alex.spring_company.model.payload.SkillPayload;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.model.view_model.SkillViewModel;
import by.vorakh.alex.spring_company.service.SkillService;

@RestController
@RequestMapping("/project")
public class SkillController {
    
    @Autowired
    private SkillService skillService;
    
    @GetMapping("/skills")
    public List<SkillViewModel> getSkills() {
        return skillService.getAll();
    }

    @GetMapping(value = "/skills/{id}")
    public SkillViewModel getSkill(@PathVariable("id") Integer id) {
        return skillService.getById(id);
    }

    @PostMapping("/skills")
    public IdViewModel createSkill(@Valid @RequestBody SkillPayload newSkill) {
	return skillService.create(newSkill);
    }

    @PutMapping(value = "/skills/{id}")
    public void updateSkill(@Valid @RequestBody SkillPayload editedSkill) {        
        skillService.update(editedSkill);
    }

    @DeleteMapping(value = "/skills/{id}")
    public void deleteSkill(@PathVariable("id") Integer id) {
	skillService.delete(id);
    }

}
