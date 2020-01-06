package by.vorakh.alex.spring_company.model.payload;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The company details for that creating and updating in the database.")
public class CompanyPayload {
    
    @PositiveOrZero
    @ApiModelProperty(value = "The company ID should be unique and is required for updating in the database.",
            example = "4")
    private int id;
    @NotNull(message = "The company name cannot be null")
    @Min(value = 2, message = "The company name does not have to contain less than 2 letters.")
    @Max(value = 40, message = "The company name does not have to contain be greater than 40 letters.")
    @Size(min = 2, max = 40)
    @ApiModelProperty(value = "The company name should be unique and contain 2-20 letters.", 
    	    required = true, example = "EvilSolutions")
    private String name;
    @ApiModelProperty(value = "The unique employee IDs list of the company from the database.", 
	    example="[1, 4, 10, 20]")
    private List<Integer> employeeIdList;
    
    public CompanyPayload() {}
   
    public CompanyPayload(@PositiveOrZero int id, @NotNull @Size(min = 2, max = 40) String name, 
	    List<Integer> employeeIdList) {
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