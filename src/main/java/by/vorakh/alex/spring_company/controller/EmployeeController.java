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
import by.vorakh.alex.spring_company.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value="Employee Management System", 
	description="Operations pertaining to employee in Employee Management System Management System")
@RestController
@RequestMapping("/project")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @ApiOperation(value = "View a list of available employees", response = List.class)
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successfully retrieved list"),
	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
    @GetMapping("/employees")
    public List<EmployeeViewModel> getEmployees() {
        return employeeService.getAll();
    }

    @ApiOperation(value = "Get an employee by Id")
    @GetMapping(value = "/employees/{id}")
    public EmployeeViewModel getEmployee(
	    @ApiParam(value = "Employee id from which employee object will retrieve", required = true) 
	    @PathVariable("id") Integer id) {
        return employeeService.getById(id);
    }
	    
    @ApiOperation(value = "Add an employee")
    @PostMapping("/employees")
    public void createEmployee(
	    @ApiParam(value = "Employee object store in database table", required = true)
	    @Valid @RequestBody EmployeePayload newEmployee) {
	employeeService.create(newEmployee);
    }

    
    
    @ApiOperation(value = "Update an employee")
    @PutMapping(value = "/employees/{id}")
    public void updateEmployee(
	    @ApiParam(value = "Employee Id to update employee object", required = true) 
	    @PathVariable(value = "id") Integer id,
	    @ApiParam(value = "Update employee object", required = true) 
	    @Valid @RequestBody EmployeePayload editedEmployee) {
	employeeService.update(id ,editedEmployee);
    }
    
    @ApiOperation(value = "Delete an employee")
    @DeleteMapping(value = "/employees/{id}")
    public void deleteEmployee(
	    @ApiParam(value = "Employee Id from which employee object will delete from database table", 
	    		required = true)
	    @PathVariable("id") Integer id) {
	employeeService.delete(id);
    }
    
}