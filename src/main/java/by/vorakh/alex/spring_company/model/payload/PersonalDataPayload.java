package by.vorakh.alex.spring_company.model.payload;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The personal data details for that creating and updating in the database.")
public class PersonalDataPayload {

    @PositiveOrZero
    @ApiModelProperty(value = "The personal data ID should be unique and is required for updating in the database", 
    	    example = "25")
    private int id;
    @NotNull(message = "The first name cannot be null")
    @Min(value = 2, message = "The first name does not have to contain less than 2 letters.")
    @Max(value = 20, message = "The first name does not have to contain be greater than 20 letters.")
    @Size(min = 2, max = 20)
    @ApiModelProperty(value = "The first name should be real and contain 2-20 letters.",  
	    example = "John", required = true)
    private String firstName;
    @NotNull(message = "The last name cannot be null")
    @Min(value = 2, message = "The last name does not have to contain less than 2 letters.")
    @Max(value = 20, message = "The last name does not have to contain be greater than 20 letters.")
    @Size(min = 2, max = 20)
    @ApiModelProperty(value = "The last name should be real and contain 2-20 letters.", 
    	    required = true, example = "Smith")
    private String lastName;
    
    public PersonalDataPayload() {}

    public PersonalDataPayload(@PositiveOrZero int id, @NotNull @Size(min = 2, max = 20) String firstName,
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