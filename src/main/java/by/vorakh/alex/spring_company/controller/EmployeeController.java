package by.vorakh.alex.spring_company.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.vorakh.alex.spring_company.model.payload.EmployeePayload;
import by.vorakh.alex.spring_company.model.view_model.EmployeeViewModel;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description="Operations pertaining to employee")
@RestController
@RequestMapping("/project")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @ApiOperation(value = "Get a list of existing employees from the database")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully"),
	    @ApiResponse(code = 500, message = "Problems with server"),
	    @ApiResponse(code = 404, message = "The resource is not found")
	})
    @GetMapping("/employees")
    public List<EmployeeViewModel> getEmployees() {
        return employeeService.getAll();
    }

    @ApiOperation(value = "Get an employee by Id from the database")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully"),
	    @ApiResponse(code = 500, message = "Problems with server"),
	    @ApiResponse(code = 404, message = "The resource is not found")
	})
    @GetMapping(value = "/employees/{id}")
    public EmployeeViewModel getEmployee(
	    @ApiParam(value = "Employee will be gotten from the database by his ID", required = true) 
	    @PathVariable("id") Integer id) {
        return employeeService.getById(id);
    }
	    
    @ApiOperation(value = "Create an employee in the database")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully"),
	    @ApiResponse(code = 500, message = "The employee was not created in the database"),
	    @ApiResponse(code = 404, message = "The resource is not found")
	})
    @PostMapping("/employees")
    public IdViewModel createEmployee(
	    @ApiParam(value = "Employee data for creating in the database.", required = true)
	    @Valid @RequestBody EmployeePayload newEmployee) {
	return employeeService.create(newEmployee);
    }

    @ApiOperation(value = "Update an existing employee in the database.")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully"),
	    @ApiResponse(code = 500, message = "The employee was not updated in the database"),
	    @ApiResponse(code = 404, message = "The resource is not found")
	})
    @PutMapping(value = "/employees/{id}")
    public void updateEmployee(
	    @ApiParam(value = "Employee data for updating in the database.", 
	            required = true) 
	    @Valid @RequestBody EmployeePayload editedEmployee) {
	employeeService.update(editedEmployee);
    }
    
    @ApiOperation(value = "Delete an existing employee by Id from the database.")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully"),
	    @ApiResponse(code = 500, message = "The employee was not delated from the database"),
	    @ApiResponse(code = 404, message = "The resource is not found")
	})
    @DeleteMapping(value = "/employees/{id}")
    public void deleteEmployee(
	    @ApiParam(value = "Employee will be deleted from the database by his ID.", 
	            required = true)
	    @PathVariable("id") Integer id) {
	employeeService.delete(id);
    }
    
}