package by.vorakh.alex.spring_company.model.external;

import java.io.Serializable;

public class ExternalSkill implements  Serializable {
    private static final long serialVersionUID = 1L;
    
    private String skillName;
    
    public ExternalSkill() {}
    
    public ExternalSkill(String skillName) {
	this.skillName = skillName;
    }

    public ExternalSkill setSkillName(String skillName) {
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
	return prime * result + ((skillName == null) ? 0 : skillName.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj || obj == null || getClass() != obj.getClass()) {
	    return false;
	}
	ExternalSkill other = (ExternalSkill) obj;
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
