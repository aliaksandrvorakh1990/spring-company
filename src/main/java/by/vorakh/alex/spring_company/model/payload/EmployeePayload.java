package by.vorakh.alex.spring_company.model.payload;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The employee details for that creating and updating in the database.")
public class EmployeePayload {
    
    @ApiModelProperty(notes = "The employee ID in the database.")
    private int id;
    @NotNull
    @ApiModelProperty(notes = "The personal data ID of the employee in the database.")
    private int personalDataId;
    @NotNull
    @ApiModelProperty(notes = "The job title ID of the employee in the database.")
    private int jobTitleId;
    @ApiModelProperty(notes = "The skill IDs list of the employee in the database.")
    private List<Integer> skillIdsList;
    
    public EmployeePayload() {}

    public EmployeePayload(int id, @NotNull int personalDataId, @NotNull int jobTitleId, 
	    List<Integer> skillIdsList) {
	this.id = id;
	this.personalDataId = personalDataId;
	this.jobTitleId = jobTitleId;
	this.skillIdsList = skillIdsList;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setPersonalDataId(int personalDataId) {
        this.personalDataId = personalDataId;
    }

    public void setJobTitleId(int jobTitleId) {
        this.jobTitleId = jobTitleId;
    }

    public void setSkillIdsList(List<Integer> skillIdsList) {
        this.skillIdsList = skillIdsList;
    }

    public int getId() {
        return id;
    }
    
    public int getPersonalDataId() {
        return personalDataId;
    }

    public int getJobTitleId() {
        return jobTitleId;
    }
    
    public List<Integer> getSkillIdsList() {
        return skillIdsList;
    }
 
}
