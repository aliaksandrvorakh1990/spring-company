package by.vorakh.alex.spring_company.model.view_model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the company for view")
public class CompanyViewModel {
    
    @ApiModelProperty(value = "The company ID in the database.", example = "4")
    private Integer id;
    @ApiModelProperty(value = "The company name.", example = "Evil-Corporation")
    private String name;
    @ApiModelProperty(value = "The employees list.")
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
