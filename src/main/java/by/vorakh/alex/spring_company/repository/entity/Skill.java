package by.vorakh.alex.spring_company.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import by.vorakh.alex.spring_company.model.SkillPayload;

@Entity
@Table(name = "employee_skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id", unique = true, nullable = false)
    private int id;

    @NotNull
    @Size(min = 2, max = 25)
    @Column(name = "skill", length = 25, nullable = false, unique = true)
    private String skillName;

    public Skill() {}
    
    public Skill(SkillPayload skillPayload) {
	this.skillName = skillPayload.getSkillName();
    }

    public Skill(int id, String skillName) {
        this.id = id;
        this.skillName = skillName;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
    
    public void setSkillName(SkillPayload skillPayload) {
        this.skillName = skillPayload.getSkillName();
    }


    public int getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }
    
    @Override
    public String toString() {
	return "Skill [id=" + id + ", skillName=" + skillName + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	result = prime * result + ((skillName == null) ? 0 : skillName.hashCode());
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
	Skill other = (Skill) obj;
	if (id != other.id) {
	    return false;
	}
	if (skillName == null) {
	    if (other.skillName != null) {
		return false;
	    }	
	} else if (!skillName.equals(other.skillName)) {
	    return false;
	}
	return true;
    }

}
