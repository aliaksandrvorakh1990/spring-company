package by.vorakh.alex.spring_company.model.payload;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The skill details for that creating and updating " + 
        "in the database.")
public class SkillPayload implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @PositiveOrZero
    @ApiModelProperty(value = "The skill's ID should be unique and " + 
            "is required for updating in the database", example = "15")
    private Integer id;
    @NotNull(message = "Skill name cannot be null")
    @Size(min = 2, max = 25, message = "The skill name should be unique and " + 
            "contain 2-25 letters.")
    @ApiModelProperty(value = "The skill name should be unique and contain " + 
            "2-25 letters.", required = true,  example = "JavaScript") 
    private String skillName;

    public SkillPayload() {}

    public SkillPayload(@PositiveOrZero Integer id, 
            @NotNull @Size(min = 2, max = 25) String skillName) {
      	this.id = id;
      	this.skillName = skillName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
    
    public Integer getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }
}
