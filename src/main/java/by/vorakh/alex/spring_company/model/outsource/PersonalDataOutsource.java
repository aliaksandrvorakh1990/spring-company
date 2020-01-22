package by.vorakh.alex.spring_company.model.outsource;

import java.io.Serializable;

public class PersonalDataOutsource implements  Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String firstName;
    private String lastName;
    
    public PersonalDataOutsource() {}
    
    public PersonalDataOutsource(String firstName, String lastName) {
	this.firstName = firstName;
	this.lastName = lastName;
    }


    public PersonalDataOutsource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonalDataOutsource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
	return "PersonalDataOutsource[firstName=" + firstName + ", lastName=" + lastName + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
	return result;
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
	PersonalDataOutsource other = (PersonalDataOutsource) obj;
	if (firstName == null) {
	    if (other.firstName != null) {
		return false;
	    }
	} else if (!firstName.equals(other.firstName)) {
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
