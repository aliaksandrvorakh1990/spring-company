package by.vorakh.alex.spring_company.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import rx.Observable;
import by.vorakh.alex.spring_company.model.payload.CompanyPayload;
import by.vorakh.alex.spring_company.model.view_model.CompanyViewModel;
import by.vorakh.alex.spring_company.model.view_model.EmployeeViewModel;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.service.CompanyService;

@Api(description="Operations pertaining to company")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 1800)
@RequestMapping("/project")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    
    @ApiOperation(value = "Get a list of existing companies from the database",
	    response = CompanyViewModel.class,
	    responseContainer = "List")
    @ApiResponses({
	    @ApiResponse(code = 500, message = "Problems with server")
	})
    @GetMapping("/companies")
    public List<CompanyViewModel> getCompanies() {
        return companyService.getAll();
    }

    @ApiOperation(value = "Get a company by Id from the database", 
	    notes = "ID has to be greater than zero.", 
	    response = CompanyViewModel.class)
    @ApiResponses({
	    @ApiResponse(code = 500, message = "The company does not exist or Problems with server")
	})
    @GetMapping("/companies/{id}")
    public CompanyViewModel getCompany(
	    @ApiParam(value = "The company will be gotten from the database by his ID", required = true) 
	    @PathVariable("id") @Positive @NotNull Integer id) {
        return companyService.getById(id);
    }

    @ApiOperation(value = "Create an company in the database",
	    response = IdViewModel.class)
    @ApiResponses({
	    @ApiResponse(code = 400, message = "Bad Request: wrong data"),
	    @ApiResponse(code = 500, 
		    message = "The company was not created in the database or Problems with server"),
	})
    @PostMapping("/companies")
    public IdViewModel createCompany(
	    @ApiParam(value = "The company data for creating in the database.", required = true)
	    @Valid @RequestBody CompanyPayload newCompany) {
	return companyService.create(newCompany);
    }

    @ApiOperation("Update an existing company in the database.")
    @ApiResponses({
	    @ApiResponse(code = 400, message = "Bad Request: wrong data"),
	    @ApiResponse(code = 500, 
		    message = "The company was not updated in the database or Problems with server")
	})
    @PutMapping("/companies/")
    public void updateCompany(
	    @ApiParam(value = "The company data for updating in the database.", required = true)
	    @Valid @RequestBody CompanyPayload editedCompany) {
        companyService.update(editedCompany);
    }

    @ApiOperation(value = "Delete an existing company by Id from the database.", 
	    notes = "ID has to be greater than zero.")
    @ApiResponses({
	    @ApiResponse(code = 500, 
		    message = "The company was not delated from the database or Problems with server")
	})
    @DeleteMapping("/companies/{id}")
    public void deleteCompany(
	    @ApiParam(value = "The company will be deleted from the database by his ID.", required = true)
	    @PathVariable("id") @Positive @NotNull Integer id) {
        companyService.delete(id);
    }
    
    @ApiOperation(value = "Create and read a random employee from external source.", 
	    notes = "ID has to be greater than zero.", 
	    response = EmployeeViewModel.class)
    @ApiResponses({
	    @ApiResponse(code = 500, message = "The company does not exist or Problems with server")
	})
    @GetMapping("/companies/{id}/random-employee")
    @ResponseBody
    public  DeferredResult<EmployeeViewModel> getRandomEmployee(@ApiParam(value = "company id", required = true)
    @PathVariable("id") @Positive @NotNull Integer id) throws InterruptedException {
	Observable<EmployeeViewModel> employeeObservable = companyService.randomEmployee(id);
	DeferredResult<EmployeeViewModel> deferredResult = new DeferredResult<>();
	
	employeeObservable.subscribe(deferredResult::setResult, deferredResult::setErrorResult);
	
	return deferredResult;
    }
}