package by.vorakh.alex.spring_company.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SkillPayload {
    
    @NotNull
    @Size(min = 2, max = 25)
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
