package by.vorakh.alex.spring_company.model.outsourse;

import java.io.Serializable;

public class SkillOutsourse implements  Serializable {
    
    private String skillName;
    
    public SkillOutsourse() {}
    
    public SkillOutsourse(String skillName) {
	
	this.skillName = skillName;
    }

    public SkillOutsourse setSkillName(String skillName) {
        this.skillName = skillName;
        return this;
    }
    
    public String getSkillName() {
        return skillName;
    }

    @Override
    public String toString() {
	return "SkillOutsourse[skillName=" + skillName + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((skillName == null) ? 0 : skillName.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return false;
	}
	if (obj == null) {
	    return false;
	}    
	if (getClass() != obj.getClass()) {
	    return false;
	}   
	SkillOutsourse other = (SkillOutsourse) obj;
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
