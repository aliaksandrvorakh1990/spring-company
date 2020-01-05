package by.vorakh.alex.spring_company.controller;

import by.vorakh.alex.spring_company.model.payload.CompanyPayload;
import by.vorakh.alex.spring_company.model.view_model.CompanyViewModel;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<CompanyViewModel> getCompanies() {
        return companyService.getAll();
    }

    @GetMapping(value = "/companies/{id}")
    public CompanyViewModel getCompany(@PathVariable("id") Integer id) {
        return companyService.getById(id);
    }

    @PostMapping("/companies")
    public IdViewModel createCompany(@Valid @RequestBody CompanyPayload newCompany) {
	return companyService.create(newCompany);
    }

    @PutMapping(value = "/companies/{id}")
    public void updateCompany(@Valid @RequestBody CompanyPayload editedCompany) {
        companyService.update(editedCompany);
    }

    @DeleteMapping(value = "/companies/{id}")
    public void deleteCompany(@PathVariable("id") Integer id) {
        companyService.delete(id);
    }

}