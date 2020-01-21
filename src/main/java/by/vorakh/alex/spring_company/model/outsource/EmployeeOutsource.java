package by.vorakh.alex.spring_company.model.outsource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class EmployeeOutsource implements  Serializable {

    private PersonalDataOutsource personalData;
    private JobTitleOutsource jobTitle;
   
    private List<SkillOutsource> skillList = new ArrayList<SkillOutsource>();
    
    public EmployeeOutsource() {}
    
    public EmployeeOutsource(PersonalDataOutsource personalData, JobTitleOutsource jobTitle,
	    List<SkillOutsource> skillList) {
	this.personalData = personalData;
	this.jobTitle = jobTitle;
	this.skillList = skillList;
    }
    

    public EmployeeOutsource setPersonalData(PersonalDataOutsource personalData) {
        this.personalData = personalData;
        return this;
    }

    public EmployeeOutsource setJobTitle(JobTitleOutsource jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public EmployeeOutsource setSkillList(List<SkillOutsource> skillList) {
        this.skillList = skillList;
        return this;
    }

 
    public PersonalDataOutsource getPersonalData() {
        return personalData;
    }

    public JobTitleOutsource getJobTitle() {
        return jobTitle;
    }

    public List<SkillOutsource> getSkillList() {
        return skillList;
    }
    
    @Override
    public String toString() {
	return "EmployeeOutsource[personalData=" + personalData + ", jobTitle=" + jobTitle
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
	EmployeeOutsource other = (EmployeeOutsource) obj;
	
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