package by.vorakh.alex.spring_company.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.vorakh.alex.spring_company.model.entity.Company;



@RestController 
public class MainRESTController {
    

    @RequestMapping("/company/{id}")
    public Company getEmployee(@PathVariable("id") Integer id) {
        return new Company(id, "test");
    }

}
