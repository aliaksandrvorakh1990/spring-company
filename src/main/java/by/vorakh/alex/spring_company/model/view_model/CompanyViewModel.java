package by.vorakh.alex.spring_company.model.view_model;

import java.util.ArrayList;
import java.util.List;

public class CompanyViewModel {
    
    private Integer id;
    private String name;
    private List<EmployeeViewModel> employeeList = new ArrayList<EmployeeViewModel>();
    
    public CompanyViewModel() {}
    
    public CompanyViewModel(Integer id, String name, List<EmployeeViewModel> employeeList) {
	this.id = id;
	this.name = name;
	this.employeeList = employeeList;
    }
    
    public CompanyViewModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public CompanyViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public CompanyViewModel setEmployeeList(List<EmployeeViewModel> employeeList) {
        this.employeeList = employeeList;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<EmployeeViewModel> getEmployeeList() {
        return employeeList;
    }

}
