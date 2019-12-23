package by.vorakh.alex.spring_company.model;

public class Skill {
    private int id;
    private String skillName;
    
    public Skill() {}
    
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
