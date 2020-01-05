package by.vorakh.alex.spring_company.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;
    @NotNull
    @OneToOne
    @JoinColumn(name="personal_data_id")
    private PersonalData personalData;
    @NotNull
    @ManyToOne
    @JoinColumn(name="title_id")
    private JobTitle jobTitle;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "employee_to_skill", 
            joinColumns = { @JoinColumn(name = "employee_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "skill_id") })
    private List<Skill> skillList = new ArrayList<Skill>();
    
    public Employee() {}
    
    public Employee(@NotNull PersonalData personalData, @NotNull JobTitle jobTitle, 
	    @NotNull List<Skill> skillList) {
	this.personalData = personalData;
	this.jobTitle = jobTitle;
	this.skillList = skillList;
    }

    public Employee(int id, @NotNull PersonalData personalData, @NotNull JobTitle jobTitle, 
	    List<Skill> skillList) {
	this.id = id;
	this.personalData = personalData;
	this.jobTitle = jobTitle;
	this.skillList = skillList;
    }

    public Employee setId(int id) {
        this.id = id;
        return this;
    }

    public Employee setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
        return this;
    }
    
    public Employee setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public Employee setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
        return this;
    }

    public int getId() {
        return id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }
    
    public List<Skill> getSkillList() {
        return skillList;
    }

    @Override
    public String toString() {
	return "Employee [id=" + id + ", personalData=" + personalData + ", jobTitle=" + jobTitle + ", skillList="
		+ skillList + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	result = prime * result + ((personalData == null) ? 0 : personalData.hashCode());
	result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
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
	Employee other = (Employee) obj;
	if (id != other.id) {
	    return false;
	}
	if (personalData == null) {
	    if (other.personalData != null) {
		return false;
	    }
	} else if (!personalData.equals(other.personalData)) {
	    return false;   
	}
	if (jobTitle == null) {
	    if (other.jobTitle != null) {
		return false;
	    }
	} else if (!jobTitle.equals(other.jobTitle)) {
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