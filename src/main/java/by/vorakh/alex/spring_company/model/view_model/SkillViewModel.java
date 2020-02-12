package by.vorakh.alex.spring_company.model.view_model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the skill for view")
public class SkillViewModel implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "The skill's ID in the database", example = "9")
    private Integer id;
    @ApiModelProperty(value = "The skill name.", example = "Java EE")
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

    @Override
    public String toString() {
        return "SkillViewModel [id=" + id + ", skillName=" + skillName + "]";
    }

    @Override
    public int hashCode() {
      	final int prime = 31;
      	int result = 1;
      	result = prime * result + id;
      	return prime * result + ((skillName == null) 
                ? 0 
                : skillName.hashCode());
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
      	SkillViewModel other = (SkillViewModel) obj;
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
