package by.vorakh.alex.spring_company.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.vorakh.alex.spring_company.model.entity.Company;
import by.vorakh.alex.spring_company.repository.service.CompanyService;


@RestController
@RequestMapping("/project")
public class CompanyController {
    
    private CompanyService companyService = new CompanyService(); 
    
    @GetMapping("/companies")
    public List<Company> getCompanies() {
	return companyService.getAll();
    }
    
    @GetMapping(value="/companies/{id}")
    public Company getCompany(@PathVariable("id") Integer id) {
        return companyService.getById(id);
    }
    
    @PostMapping("/companies")
    public void createCompany(@Valid @RequestBody Company newCompany) {
	companyService.create(newCompany);
    }
    
    @PutMapping(value="/companies/{id}")
    public void updateCompany( @PathVariable(value = "id") Integer id, 
	    @Valid @RequestBody Company editedCompany) {
	Company company = companyService.getById(id);
	
	company.setName(editedCompany.getName());
	
	companyService.update(company);
    }
    
    @DeleteMapping(value="/companies/{id}")
    public void updateCompany(@PathVariable("id") Integer id) {
	companyService.delete(id);
    }

}
