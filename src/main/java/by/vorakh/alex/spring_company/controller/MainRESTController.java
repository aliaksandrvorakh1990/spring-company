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
    
    @Autowired
    private CompanyDAO companyDAO;
    
    @RequestMapping(value = "/companies", 
            method = RequestMethod.GET, 
            produces = { MediaType.APPLICATION_JSON_VALUE, 
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Company> getCompanies() {
        List<Company> list = companyDAO.getAllCompanies();
        return list;
    }
    
    @RequestMapping(value = "/company/{id}", 
            method = RequestMethod.GET, 
            produces = { MediaType.APPLICATION_JSON_VALUE, 
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Company getEmployee(@PathVariable("id") Integer id) {
        return companyDAO.getCompany(id);
    }
    

    @RequestMapping(value = "/company", 
            method = RequestMethod.POST, 
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Company addCompany(@RequestBody CompanyForm form) {
	System.out.println("(Service Side) Creating company with id: " + form.getId());
	return companyDAO.addCompany(form);
    }
    
    @RequestMapping(value = "/company", 
            method = RequestMethod.PUT, 
            produces = { MediaType.APPLICATION_JSON_VALUE, 
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Company updateCompany(@RequestBody CompanyForm form) {
        System.out.println("(Service Side) Editing company with id: " + form.getId());
	return companyDAO.updateCompany(form);
    }
    
    
    @RequestMapping(value = "/company/{id}", 
            method = RequestMethod.DELETE, 
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteEmployee(@PathVariable("id")  Integer id) {
  
        System.out.println("(Service Side) Deleting company with Id: " + id);
  
        companyDAO.deleteCompany(id);
    }
    
}
