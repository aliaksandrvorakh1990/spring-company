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

import by.vorakh.alex.spring_company.model.payload.PersonalDataPayload;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.model.view_model.PersonalDataViewModel;
import by.vorakh.alex.spring_company.service.PersonalDataService;
import io.swagger.annotations.Api;

@Api(description="Operations pertaining to personal data")
@RestController
@RequestMapping("/project")
public class PersonalDataController {
    
    @Autowired
    private PersonalDataService personalDataService;
    
    @GetMapping("/people")
    public List<PersonalDataViewModel> getPersonalDatas() {
        return personalDataService.getAll();
    }

    @GetMapping(value = "/people/{id}")
    public PersonalDataViewModel getPersonalData(@PathVariable("id") Integer id) {
        return personalDataService.getById(id);
    }

    @PostMapping("/people")
    public IdViewModel createPersonalData(@Valid @RequestBody PersonalDataPayload newPersonalData) {
	return personalDataService.create(newPersonalData);
    }

    @PutMapping(value = "/people/{id}")
    public void updatePersonalData(@Valid @RequestBody PersonalDataPayload editedPersonalData) {
        personalDataService.update(editedPersonalData);
    }

    @DeleteMapping(value = "/people/{id}")
    public void deletePersonalData(@PathVariable("id") Integer id) {
	personalDataService.delete(id);
    }
}
