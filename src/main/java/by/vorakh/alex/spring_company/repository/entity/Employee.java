package by.vorakh.alex.spring_company.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import by.vorakh.alex.spring_company.model.EmployeePayload;

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
    
    public Employee(EmployeePayload employeePayload) {
	this.personalData = employeePayload.getPersonalData();
    }

    public Employee(int id, PersonalData personalData) {
	this.id = id;
	this.personalData = personalData;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
    
    public void set(EmployeePayload employeePayload) {
	 this.personalData = employeePayload.getPersonalData();
    }

    public int getId() {
        return id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

}
