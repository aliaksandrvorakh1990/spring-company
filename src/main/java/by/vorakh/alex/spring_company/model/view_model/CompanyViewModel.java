package by.vorakh.alex.spring_company.model.view_model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the company for view")
public class CompanyViewModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "The company ID in the database.", example = "4")
    private Integer id;
    @ApiModelProperty(value = "The company name.", example = "Evil-Corporation")
    private String name;
    @ApiModelProperty("The employees list.")
    private List<EmployeeViewModel> employeeList = new ArrayList<>();
    
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
    
        @Override
    public String toString() {
	return "CompanyViewModel [id=" + id + ", name=" + name + ", employeeList=" + employeeList + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id; 
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return prime * result + ((employeeList == null) ? 0 : employeeList.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null || getClass() != obj.getClass()) {
	    return false;
	}
	CompanyViewModel other = (CompanyViewModel) obj;
	if (id != other.id) {
	    return false;
	} 
	if (name == null) {
	    if (other.name != null) {
		return false;
	    }
	} else if (!name.equals(other.name)) {
	    return false;
	}  
	if (employeeList == null) {
	    if (other.employeeList != null) {
		return false;
	    }
	} else if (!employeeList.equals(other.employeeList)) {
	    return false;
	}
	return true;
    }
}
