package by.vorakh.alex.spring_company.model.outsource;

import java.io.Serializable;

public class SkillOutsource implements  Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String skillName;
    
    public SkillOutsource() {}
    
    public SkillOutsource(String skillName) {
	
	this.skillName = skillName;
    }

    public SkillOutsource setSkillName(String skillName) {
        this.skillName = skillName;
        return this;
    }
    
    public String getSkillName() {
        return skillName;
    }

    @Override
    public String toString() {
	return "SkillOutsource[skillName=" + skillName + "]";
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
	SkillOutsource other = (SkillOutsource) obj;
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
