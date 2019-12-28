package by.vorakh.alex.spring_company.controller;

import by.vorakh.alex.spring_company.model.CompanyPayload;
import by.vorakh.alex.spring_company.repository.entity.Company;
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
    public List<Company> getCompanies() {
        return companyService.getAll();
    }

    @GetMapping(value = "/companies/{id}")
    public Company getCompany(@PathVariable("id") Integer id) {
        return companyService.getById(id);
    }

    @PostMapping("/companies")
    public void createCompany(@Valid @RequestBody CompanyPayload newCompany) {
        companyService.create(newCompany);
    }

    @PutMapping(value = "/companies/{id}")
    public void updateCompany(@PathVariable(value = "id") Integer id,
                              @Valid @RequestBody CompanyPayload editedCompany) {
        companyService.update(id ,editedCompany);
    }

    @DeleteMapping(value = "/companies/{id}")
    public void deleteCompany(@PathVariable("id") Integer id) {
        companyService.delete(id);
    }

}
