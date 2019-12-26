package by.vorakh.alex.spring_company.controller;


import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.vorakh.alex.spring_company.model.entity.Company;
import by.vorakh.alex.spring_company.repository.service.CompanyService;


@RestController 
public class CompanyController {
    
    private CompanyService companyService = new CompanyService(); 
    
    @RequestMapping(value="/companies", method = RequestMethod.GET)
    public List<Company> getCompanies() {
	return companyService.getAll();
    }
    
    @RequestMapping(value="/company/{id}", method = RequestMethod.GET)
    public Company getCompany(@PathVariable("id") Integer id) {
        return companyService.getById(id);
    }
    
    @RequestMapping(value="/company/new", method = RequestMethod.POST)
    public void createCompany(Company newCompany) {
	companyService.create(newCompany);
    }
    
    @RequestMapping(value="/company/edit/{id}", method = RequestMethod.PUT)
    public void updateCompany(Company company) {
	companyService.update(company);
    }
    
    @RequestMapping(value="/company/{id}", method = RequestMethod.DELETE)
    public void updateCompany(@PathVariable("id") Integer id) {
	companyService.delete(id);
    }

}
