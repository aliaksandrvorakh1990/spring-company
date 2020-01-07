package by.vorakh.alex.spring_company.controller;

import javax.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import by.vorakh.alex.spring_company.model.payload.CompanyPayload;
import by.vorakh.alex.spring_company.model.view_model.CompanyViewModel;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.service.CompanyService;

@Api(description="Operations pertaining to company")
@RestController
@RequestMapping("/project")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;
    
    @ApiOperation(value = "Get a list of existing companies from the database",
	    response = CompanyViewModel.class,
	    responseContainer = "List", code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, message = "Problems with server")
	})
    @GetMapping("/companies")
    public List<CompanyViewModel> getCompanies() {
        return companyService.getAll();
    }

    @ApiOperation(value = "Get a company by Id from the database", 
	    notes = "ID has to be greater than zero.", 
	    response = CompanyViewModel.class , code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, message = "The company does not exist or Problems with server")
	})
    @GetMapping(value = "/companies/{id}")
    public CompanyViewModel getCompany(
	    @ApiParam(value = "The company will be gotten from the database by his ID", required = true) 
	    @PathVariable("id") Integer id) {
        return companyService.getById(id);
    }

    @ApiOperation(value = "Create an company in the database",
	    response = IdViewModel.class, code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, 
		    message = "The company was not created in the database or Problems with server"),
	})
    @PostMapping("/companies")
    public IdViewModel createCompany(
	    @ApiParam(value = "The company data for creating in the database.", required = true)
	    @Valid @RequestBody CompanyPayload newCompany) {
	return companyService.create(newCompany);
    }

    @ApiOperation(value = "Update an existing company in the database.", code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, 
		    message = "The company was not updated in the database or Problems with server")
	})
    @PutMapping(value = "/companies/{id}")
    public void updateCompany(
	    @ApiParam(value = "The company data for updating in the database.", required = true)
	    @Valid @RequestBody CompanyPayload editedCompany) {
        companyService.update(editedCompany);
    }

    @ApiOperation(value = "Delete an existing company by Id from the database.", 
	    notes = "ID has to be greater than zero.", code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, 
		    message = "The company was not delated from the database or Problems with server")
	})
    @DeleteMapping(value = "/companies/{id}")
    public void deleteCompany(
	    @ApiParam(value = "The company will be deleted from the database by his ID.", required = true)
	    @PathVariable("id") Integer id) {
        companyService.delete(id);
    }

}