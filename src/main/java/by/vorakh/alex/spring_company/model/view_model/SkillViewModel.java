package by.vorakh.alex.spring_company.model.view_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the skill for view")
public class SkillViewModel {
    
    @ApiModelProperty(notes = "The skill's ID in the database")
    private int id;
    @ApiModelProperty(notes = "The skill name.")
    private String skillName;
    
    public SkillViewModel() {}
    
    public SkillViewModel(int id, String skillName) {
	this.id = id;
	this.skillName = skillName;
    }
    
    public SkillViewModel setId(int id) {
        this.id = id;
        return this;
    }

    public SkillViewModel setSkillName(String skillName) {
        this.skillName = skillName;
        return this;
    }
    
    public int getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }

}
