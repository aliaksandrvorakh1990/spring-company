package by.vorakh.alex.spring_company.model.payload;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The employee details for that creating and updating in the database.")
public class EmployeePayload implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @PositiveOrZero
    @ApiModelProperty(value = "The employee ID should be unique and is required for updating in the database.",
            example = "28")
    private Integer id;
    @Positive(message = "The personal data Id has to be greater than zero.")
    @ApiModelProperty(value = "The personal data ID of the employee from the database.",
	    example = "2", required = true)
    private Integer personalDataId;
    @Positive(message = "The job title Id has to be greater than zero.")
    @ApiModelProperty(value = "The job title ID of the employee from  the database.", 
    	     example = "42", required = true)
    private Integer jobTitleId;
    @ApiModelProperty(value = "The unique skill IDs list of the employee from the database.", 
    	    example="[1, 4, 10, 20]")
    private List<Integer> skillIdsList;
    
    public EmployeePayload() {}

    public EmployeePayload(@PositiveOrZero Integer id, @Positive Integer personalDataId, @Positive Integer jobTitleId, 
	    List<Integer> skillIdsList) {
	this.id = id;
	this.personalDataId = personalDataId;
	this.jobTitleId = jobTitleId;
	this.skillIdsList = skillIdsList;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setPersonalDataId(Integer personalDataId) {
        this.personalDataId = personalDataId;
    }

    public void setJobTitleId(Integer jobTitleId) {
        this.jobTitleId = jobTitleId;
    }

    public void setSkillIdsList(List<Integer> skillIdsList) {
        this.skillIdsList = skillIdsList;
    }

    public Integer getId() {
        return id;
    }
    
    public Integer getPersonalDataId() {
        return personalDataId;
    }

    public Integer getJobTitleId() {
        return jobTitleId;
    }
    
    public List<Integer> getSkillIdsList() {
        return skillIdsList;
    }
 
}
