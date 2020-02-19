package by.vorakh.alex.spring_company.model.view_model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the employee for view.")
public class EmployeeViewModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "The employee ID in the database.", 
            example = "11")
    private Integer id;
    private PersonalDataViewModel personalData;
    private JobTitleViewModel jobTitle;
    @ApiModelProperty("The employee's skills list.")
    private List<SkillViewModel> skillList = new ArrayList<>();
    
    public EmployeeViewModel() {}
    
    public EmployeeViewModel(Integer id, PersonalDataViewModel personalData, 
            JobTitleViewModel jobTitle, List<SkillViewModel> skillList) {
      	this.id = id;
      	this.personalData = personalData;
      	this.jobTitle = jobTitle;
      	this.skillList = skillList;
    }
    
    public EmployeeViewModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public EmployeeViewModel setPersonalData(PersonalDataViewModel 
            personalData) {
        this.personalData = personalData;
        return this;
    }

    public EmployeeViewModel setJobTitle(JobTitleViewModel jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public EmployeeViewModel setSkillList(List<SkillViewModel> skillList) {
        this.skillList = skillList;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public PersonalDataViewModel getPersonalData() {
        return personalData;
    }

    public JobTitleViewModel getJobTitle() {
        return jobTitle;
    }

    public List<SkillViewModel> getSkillList() {
        return skillList;
    }
    
    @Override
    public String toString() {
        return "EmployeeViewModel [id=" + id + ", personalData=" + 
                personalData + ", jobTitle=" + jobTitle	+ 
                ", skillList=" + skillList + "]";
    }

    @Override
    public int hashCode() {
      	final int prime = 31;
      	int result = 1;
      	result = prime * result + id;
      	result = prime * result + ((jobTitle == null) 
                ? 0 
                : jobTitle.hashCode());
      	result = prime * result + ((personalData == null) 
                ? 0 
                : personalData.hashCode());
      	return prime * result + ((skillList == null) 
                ? 0 
                : skillList.hashCode());
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
      	EmployeeViewModel other = (EmployeeViewModel) obj;
      	if (id != other.id) {
      	    return false;
      	}
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
