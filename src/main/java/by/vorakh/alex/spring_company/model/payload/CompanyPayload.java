package by.vorakh.alex.spring_company.model.payload;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompanyPayload {
    
    @NotNull
    @Size(min = 2, max = 40)
    private String name;
    
    private List<Integer> employeeIdList;
    
    public CompanyPayload() {}
    
    public CompanyPayload(@NotNull @Size(min = 2, max = 40) String name, List<Integer> employeeIdList) {
	this.name = name;
	this.employeeIdList = employeeIdList;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmployeeIdList(List<Integer> employeeIdList) {
        this.employeeIdList = employeeIdList;
    }

    public String getName() {
        return name;
    }
    
    public List<Integer> getEmployeeIdList() {
        return employeeIdList;
    }
}
