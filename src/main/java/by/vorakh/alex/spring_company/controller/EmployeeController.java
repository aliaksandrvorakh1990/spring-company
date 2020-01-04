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
import by.vorakh.alex.spring_company.repository.entity.Employee;
import by.vorakh.alex.spring_company.service.EmployeeService;

@RestController
@RequestMapping("/project")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getAll();
    }

    @GetMapping(value = "/employees/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        return employeeService.getById(id);
    }

    @PostMapping("/employees")
    public void createEmployee(@Valid @RequestBody EmployeePayload newEmployee) {
	employeeService.create(newEmployee);
    }

    @PutMapping(value = "/employees/{id}")
    public void updateEmployee(@PathVariable(value = "id") Integer id,
                              @Valid @RequestBody EmployeePayload editedEmployee) {
	employeeService.update(id ,editedEmployee);
    }

    @DeleteMapping(value = "/employees/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id) {
	employeeService.delete(id);
    }
}
