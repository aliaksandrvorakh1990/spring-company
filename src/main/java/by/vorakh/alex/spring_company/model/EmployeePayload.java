package by.vorakh.alex.spring_company.model;

import javax.validation.constraints.NotNull;

public class EmployeePayload {
    
    @NotNull
    private int personalDataId;
    
    public EmployeePayload() {}

    public EmployeePayload(@NotNull int personalDataId) {
	this.personalDataId = personalDataId;
    }

    public int getPersonalDataId() {
        return personalDataId;
    }

    public void setPersonalDataId(int personalDataId) {
        this.personalDataId = personalDataId;
    }

}
