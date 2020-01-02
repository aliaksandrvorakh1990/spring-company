package by.vorakh.alex.spring_company.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonalDataPayload {
    
    @NotNull
    @Size(min = 2, max = 20)
    private String firstName;
    @NotNull
    @Size(min = 2, max = 20)
    private String lastName;
    
    public PersonalDataPayload() {}
        
    public PersonalDataPayload(@NotNull @Size(min = 2, max = 20) String firstName,
	    @NotNull @Size(min = 2, max = 20) String lastName) {
	this.firstName = firstName;
	this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
