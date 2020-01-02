package by.vorakh.alex.spring_company.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "personal_data")
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_data_id", unique = true, nullable = false)
    private int id;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;
    
    public PersonalData() {}
    
    public PersonalData(@NotNull @Size(min = 2, max = 20) String firstName,
	    @NotNull @Size(min = 2, max = 20) String lastName) {
	this.firstName = firstName;
	this.lastName = lastName;
    }

    public PersonalData(int id, @NotNull @Size(min = 2, max = 20) String firstName,
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

    @Override
    public String toString() {
	return "PersonalData [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
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
	PersonalData other = (PersonalData) obj;
	if (id != other.id) {
	    return false;
	}
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
