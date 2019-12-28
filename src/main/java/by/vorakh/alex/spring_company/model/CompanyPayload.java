package by.vorakh.alex.spring_company.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompanyPayload {
    
    @NotNull
    @Size(min = 2, max = 40)
    private String name;
    
    public CompanyPayload() {}
    
    public CompanyPayload(String name) {
	this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
       
    public String getName() {
        return name;
    }
}
