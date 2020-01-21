package by.vorakh.alex.spring_company.model.outsourse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class EmployeeOutsourse implements  Serializable {

    private PersonalDataOutsourse personalData;
    private JobTitleOutsourse jobTitle;
   
    private List<SkillOutsourse> skillList = new ArrayList<SkillOutsourse>();
    
    public EmployeeOutsourse() {}
    
    public EmployeeOutsourse(PersonalDataOutsourse personalData, JobTitleOutsourse jobTitle,
	    List<SkillOutsourse> skillList) {
	this.personalData = personalData;
	this.jobTitle = jobTitle;
	this.skillList = skillList;
    }
    

    public EmployeeOutsourse setPersonalData(PersonalDataOutsourse personalData) {
        this.personalData = personalData;
        return this;
    }

    public EmployeeOutsourse setJobTitle(JobTitleOutsourse jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public EmployeeOutsourse setSkillList(List<SkillOutsourse> skillList) {
        this.skillList = skillList;
        return this;
    }

 
    public PersonalDataOutsourse getPersonalData() {
        return personalData;
    }

    public JobTitleOutsourse getJobTitle() {
        return jobTitle;
    }

    public List<SkillOutsourse> getSkillList() {
        return skillList;
    }
    
    @Override
    public String toString() {
	return "EmployeeOutsourse[personalData=" + personalData + ", jobTitle=" + jobTitle
		+ ", skillList=" + skillList + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
	result = prime * result + ((personalData == null) ? 0 : personalData.hashCode());
	result = prime * result + ((skillList == null) ? 0 : skillList.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	EmployeeOutsourse other = (EmployeeOutsourse) obj;
	
	if (jobTitle == null) {
	    if (other.jobTitle != null) {
		return false;
	    }
	} else if (!jobTitle.equals(other.jobTitle)) {
	    return false;
	}
	if (personalData == null) {
	    if (other.personalData != null) {
		return false;
	    }
	} else if (!personalData.equals(other.personalData)) {
	    return false;
	}
	if (skillList == null) {
	    if (other.skillList != null) {
		return false;
	    }
	} else if (!skillList.equals(other.skillList)) {
	    return false;
	}
	return true;
    }

}