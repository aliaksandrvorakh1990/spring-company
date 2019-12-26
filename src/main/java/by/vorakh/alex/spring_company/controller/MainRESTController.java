package by.vorakh.alex.spring_company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import by.vorakh.alex.spring_company.model.Company;
import by.vorakh.alex.spring_company.model.CompanyForm;
import by.vorakh.alex.spring_company.repository.dao.CompanyDAO;


@RestController 
public class MainRESTController {
    
   
   
    @RequestMapping("/company/{id}")
    public Company getEmployee(@PathVariable("id") Integer id) {
        return new Company(id, "test");
    }

}
