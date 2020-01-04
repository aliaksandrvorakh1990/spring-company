package by.vorakh.alex.spring_company.model.view_model;

public class PersonalDataViewModel {
    private int id;
    private String firstName;
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
