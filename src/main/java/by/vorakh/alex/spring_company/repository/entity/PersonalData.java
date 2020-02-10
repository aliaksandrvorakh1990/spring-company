package by.vorakh.alex.spring_company.repository.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "personal_data")
public class PersonalData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_data_id", unique = true, nullable = false)
    private Integer id;
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

    public PersonalData(@NotNull Integer id, 
            @NotNull @Size(min = 2, max = 20) String firstName, 
            @NotNull @Size(min = 2, max = 20) String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonalData setId(Integer id) {
        this.id = id;
        return this;
    }

    public PersonalData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonalData setLastName(String lastName) {
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
        return String.format("PersonalData [id=%s, firstName=%s, lastName=%s]",
                id, firstName, lastName);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((firstName == null) ?
                0 :
                firstName.hashCode());
        return prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
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