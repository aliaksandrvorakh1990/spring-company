package by.vorakh.alex.spring_company.model.view_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the personal data of an employee for view.")
public class PersonalDataViewModel {
    
    @ApiModelProperty(notes = "The personal data ID in the database.")
    private int id;
    @ApiModelProperty(notes = "The employee first name.")
    private String firstName;
    @ApiModelProperty(notes = "The employee last name.")
    private String lastName;
    
    public PersonalDataViewModel() {}
    
    public PersonalDataViewModel(int id, String firstName, String lastName) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
    }

    public PersonalDataViewModel setId(int id) {
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
