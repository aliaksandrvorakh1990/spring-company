package by.vorakh.alex.spring_company.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private int id;
    
   
    private PersonalData pesonalData;
    
    
    private JobTitle jobTitle;
    
    private List<Skill> skills;
    
    public Employee() {}
    
    
    public Employee(int id, PersonalData pesonalData, JobTitle jobTitle, List<Skill> skills) {
	this.id = id;
	this.pesonalData = pesonalData;
	this.jobTitle = jobTitle;
	this.skills = skills;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPesonalData(PersonalData pesonalData) {
        this.pesonalData = pesonalData;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public PersonalData getPesonalData() {
        return pesonalData;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
	return "Employee [id=" + id + ", pesonalData=" + pesonalData + ", jobTitle=" + jobTitle + ", skills=" + skills
		+ "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
	result = prime * result + ((pesonalData == null) ? 0 : pesonalData.hashCode());
	result = prime * result + ((skills == null) ? 0 : skills.hashCode());
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
	Employee other = (Employee) obj;
	if (id != other.id) {
	    return false;
	}
	if (pesonalData == null) {
	    if (other.pesonalData != null) {
		return false;
	    }
		
	} else if (!pesonalData.equals(other.pesonalData)) {
	    return false;
	}
	if (jobTitle == null) {
	    if (other.jobTitle != null) {
		return false;
	    }
	} else if (!jobTitle.equals(other.jobTitle)) {
	    return false;
	}  
	if (skills == null) {
	    if (other.skills != null) {
		return false;
	    }	
	} else if (!skills.equals(other.skills)) {
	    return false;
	}  
	return true;
    }

}
