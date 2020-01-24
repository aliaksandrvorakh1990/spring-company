package by.vorakh.alex.spring_company.model.payload;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The company details for that creating and updating in the database.")
public class CompanyPayload implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @PositiveOrZero
    @ApiModelProperty(value = "The company ID should be unique and is required for updating in the database.",
            example = "4")
    private Integer id;
    @NotNull(message = "The company name cannot be null")
    @Size(min = 2, max = 40, message = "The company name should be unique and contain 2-20 letters.")
    @ApiModelProperty(value = "The company name should be unique and contain 2-20 letters.", 
    	    required = true, example = "EvilSolutions")
    private String name;
    @ApiModelProperty(value = "The unique employee IDs list of the company from the database.", 
	    example="[1, 4, 10, 20]")
    private List<Integer> employeeIdList;
    
    public CompanyPayload() {}
   
    public CompanyPayload(@PositiveOrZero Integer id, @NotNull @Size(min = 2, max = 40) String name, 
	    List<Integer> employeeIdList) {
	this.id = id;
	this.name = name;
	this.employeeIdList = employeeIdList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmployeeIdList(List<Integer> employeeIdList) {
        this.employeeIdList = employeeIdList;
    }

    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Integer> getEmployeeIdList() {
        return employeeIdList;
    }
    
}