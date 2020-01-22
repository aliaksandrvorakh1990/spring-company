package by.vorakh.alex.spring_company.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import by.vorakh.alex.spring_company.model.payload.EmployeePayload;
import by.vorakh.alex.spring_company.model.view_model.EmployeeViewModel;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.service.EmployeeService;

@Api(description="Operations pertaining to company")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 1800)
@RequestMapping("/project")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @ApiOperation(value = "Get a list of existing employees from the database",
	    response = EmployeeViewModel.class,
	    responseContainer = "List", code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, message = "Problems with server")
	})
    @GetMapping("/employees")
    public List<EmployeeViewModel> getEmployees() {
        return employeeService.getAll();
    }

    
    @ApiOperation(value = "Get an employee by Id from the database", 
	    notes = "ID has to be greater than zero.", 
	    response = EmployeeViewModel.class , code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, message = "The employee does not exist or Problems with server")
	})
    @GetMapping(value = "/employees/{id}")
    public EmployeeViewModel getEmployee(
	    @ApiParam(value = "Employee will be gotten from the database by his ID", required = true) 
	    @PathVariable("id") @Positive int id) {
        return employeeService.getById(id);
    }
	    
    @ApiOperation(value = "Create an employee in the database",
	    response = IdViewModel.class, code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, 
		    message = "The employee was not created in the database or Problems with server"),
	})
    @PostMapping("/employees")
    public IdViewModel createEmployee(
	    @ApiParam(value = "The employee data for creating in the database.", required = true)
	    @Valid @RequestBody EmployeePayload newEmployee) {
	return employeeService.create(newEmployee);
    }

    @ApiOperation(value = "Update an existing employee in the database.", code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, 
		    message = "The employee was not updated in the database or Problems with server")
	})
    @PutMapping(value = "/employees/{id}")
    public void updateEmployee(
	    @ApiParam(value = "The employee data for updating in the database.", required = true) 
	    @Valid @RequestBody EmployeePayload editedEmployee) {
	employeeService.update(editedEmployee);
    }
    
    @ApiOperation(value = "Delete an existing employee by Id from the database.", 
	    notes = "ID has to be greater than zero.", code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, 
		    message = "The employee was not delated from the database or Problems with server")
	})
    @DeleteMapping(value = "/employees/{id}")
    public void deleteEmployee(
	    @ApiParam(value = "The employee will be deleted from the database by his ID.", required = true)
	    @PathVariable("id") @Positive int id) {
	employeeService.delete(id);
    }
    
}