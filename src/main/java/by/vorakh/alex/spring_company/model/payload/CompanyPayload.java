package by.vorakh.alex.spring_company.model.payload;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The company details for that creating and updating in the database.")
public class CompanyPayload {
    
    @ApiModelProperty(notes = "The company ID in the database.")
    private int id;
    @NotNull
    @Size(min = 2, max = 40)
    @ApiModelProperty(notes = "The company name has to have a length between 2 and 40 letters.")
    private String name;
    @ApiModelProperty(notes = "The employee IDs list of the company in the database.")
    private List<Integer> employeeIdList;
    
    public CompanyPayload() {}
   
    public CompanyPayload(int id, @NotNull @Size(min = 2, max = 40) String name, List<Integer> employeeIdList) {
	this.id = id;
	this.name = name;
	this.employeeIdList = employeeIdList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmployeeIdList(List<Integer> employeeIdList) {
        this.employeeIdList = employeeIdList;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Integer> getEmployeeIdList() {
        return employeeIdList;
    }
    
}