package by.vorakh.alex.spring_company.model.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The personal data details for that creating and updating in the database.")
public class PersonalDataPayload {
    
    @ApiModelProperty(notes = "The personal data ID in the database.")
    private int id;
    @NotNull
    @Size(min = 2, max = 20)
    @ApiModelProperty(notes = "The first name has to have a length between 2 and 20 letters.")
    private String firstName;
    @NotNull
    @Size(min = 2, max = 20)
    @ApiModelProperty(notes = "The last name has to have a length between 2 and 20 letters.")
    private String lastName;
    
    public PersonalDataPayload() {}

    public PersonalDataPayload(int id, @NotNull @Size(min = 2, max = 20) String firstName,
	    @NotNull @Size(min = 2, max = 20) String lastName) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
    }

   public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}