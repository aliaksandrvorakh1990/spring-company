package by.vorakh.alex.spring_company.model;

import javax.validation.constraints.NotNull;

import by.vorakh.alex.spring_company.repository.entity.PersonalData;

public class EmployeePayload {
    
    @NotNull
    private PersonalData personalData;
    
    public EmployeePayload() {}

    public EmployeePayload(PersonalData personalData) {
	this.personalData = personalData;
    }
    
    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    
    

}
