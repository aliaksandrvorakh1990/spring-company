package by.vorakh.alex.spring_company.model.view_model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The created object's ID.")
public class IdViewModel implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "The ID is an object that was created " + 
            "in the database.", example = "12")
    private Integer id;
    
    public IdViewModel() {}

    public IdViewModel(Integer id) {
        this.id = id;
    }
    
    public IdViewModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "IdViewModel [id=" + id + "]";
    }

    @Override
    public int hashCode() {
      	final int prime = 31;
      	int result = 1;
      	return prime * result + id;
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
      	IdViewModel other = (IdViewModel) obj;
      	return id == other.id;
    }
}
