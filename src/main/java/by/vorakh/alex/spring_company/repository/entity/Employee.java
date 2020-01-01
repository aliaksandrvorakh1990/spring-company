package by.vorakh.alex.spring_company.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private int id;
    
    @NotNull
    @OneToOne
    @JoinColumn(name="personal_data_id")
    private PersonalData personalData;

    public Employee() {}
   
    public Employee(PersonalData personalData) {
	this.personalData = personalData;
    }

    public Employee(int id, PersonalData personalData) {
	this.id = id;
	this.personalData = personalData;
    }
    
    public Employee setId(int id) {
        this.id = id;
        return this;
    }

    public Employee setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
        return this;
    }
  
    public int getId() {
        return id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

}
