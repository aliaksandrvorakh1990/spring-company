package by.vorakh.alex.spring_company.model.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The skill details for that creating and updating in the database.")
public class SkillPayload {
    
    @ApiModelProperty(notes = "The skill's ID in the database")
    private int id;
    @NotNull
    @Size(min = 2, max = 25)
    @ApiModelProperty(notes = "The skill name has to have a length between 2 and 20 letters.")
    private String skillName;

    public SkillPayload() {}

    public SkillPayload(int id, @NotNull @Size(min = 2, max = 25) String skillName) {
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
    
}
