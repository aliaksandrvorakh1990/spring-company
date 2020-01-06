package by.vorakh.alex.spring_company.model.payload;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The skill details for that creating and updating in the database.")
public class SkillPayload {
   
    @PositiveOrZero
    @ApiModelProperty(value = "The skill's ID should be unique and is required for updating in the database", 
    	    example = "15")
    private int id;
    @NotNull(message = "Skill name cannot be null")
    @Min(value = 2, message = "Skill name does not have to contain less than 2 letters.")
    @Max(value = 25, message = "Skill name does not have to contain be greater than 25 letters.")
    @Size(min = 2, max = 25)
    @ApiModelProperty(value = "The skill name should be unique and contain 2-25 letters.", 
    	    required = true,  example = "JavaScript") 
    private String skillName;

    public SkillPayload() {}

    public SkillPayload(@PositiveOrZero int id, @NotNull @Size(min = 2, max = 25) String skillName) {
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
