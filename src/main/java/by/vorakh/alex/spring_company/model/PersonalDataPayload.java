package by.vorakh.alex.spring_company.model;

public class PersonalDataPayload {

    private String firstName;
    private String lastName;
    
    public PersonalDataPayload() {}
    
    public PersonalDataPayload(String firstName, String lastName) {
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
