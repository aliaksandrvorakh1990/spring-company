package by.vorakh.alex.spring_company.model.view_model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the personal data of an employee " + 
        "for view.")
public class PersonalDataViewModel implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "The personal data ID in the database.", 
            example = "53")
    private Integer id; 
    @ApiModelProperty(value = "The employee first name.", example = "Nick")
    private String firstName;
    @ApiModelProperty(value = "The employee last name.", example = "Hawk")
    private String lastName;
    
    public PersonalDataViewModel() {}
    
    public PersonalDataViewModel(Integer id, String firstName, 
            String lastName) {
      	this.id = id;
      	this.firstName = firstName;
      	this.lastName = lastName;
    }

    public PersonalDataViewModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public PersonalDataViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonalDataViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    
    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "PersonalDataViewModel [id=" + id + ", firstName=" + firstName + 
                ", lastName=" + lastName + "]";
    }

    @Override
    public int hashCode() {
      	final int prime = 31;
      	int result = 1;
      	result = prime * result + id;
      	result = prime * result + ((firstName == null) 
                ? 0 
                : firstName.hashCode());
      	return prime * result + ((lastName == null) 
                ? 0 
                : lastName.hashCode());
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
      	PersonalDataViewModel other = (PersonalDataViewModel) obj;
      	if (firstName == null) {
      	    if (other.firstName != null) {
      		      return false;
      	    }
      	} else if (!firstName.equals(other.firstName)) {
      	    return false;
      	}
      	    
      	if (id != other.id) {
      	    return false;
      	}
      	if (lastName == null) {
      	    if (other.lastName != null) {
      		      return false;
      	    }
      	} else if (!lastName.equals(other.lastName)) {
      	    return false;
      	}
      	    
      	return true;
    }
}
