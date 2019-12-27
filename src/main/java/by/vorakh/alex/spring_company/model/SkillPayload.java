package by.vorakh.alex.spring_company.model;

public class SkillPayload {
    private String skillName;

    public SkillPayload() {}
    
    public SkillPayload(String skillName) {
	this.skillName = skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
    
    public String getSkillName() {
        return skillName;
    }
    
}
