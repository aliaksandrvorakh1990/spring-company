package by.vorakh.alex.spring_company.model.payload;

import java.util.List;

import javax.validation.constraints.NotNull;

public class EmployeePayload {
    
    @NotNull
    private int personalDataId;
    
    @NotNull
    private int jobTitleId;
    
    private List<Integer> skillIdsList;
    
    public EmployeePayload() {}

    public EmployeePayload(@NotNull int personalDataId, @NotNull int jobTitleId, List<Integer> skillIdsList) {
	this.personalDataId = personalDataId;
	this.jobTitleId = jobTitleId;
	this.skillIdsList = skillIdsList;
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

    public int getPersonalDataId() {
        return personalDataId;
    }

    public int getJobTitleId() {
        return jobTitleId;
    }
    
    public List<Integer> getSkillIdsList() {
        return skillIdsList;
    }

    @Override
    public String toString() {
	return "EmployeePayload [personalDataId=" + personalDataId + ", jobTitleId=" + jobTitleId + ", skillIdsList="
		+ skillIdsList + "]";
    }

    
}
