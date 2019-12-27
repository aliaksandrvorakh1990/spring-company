package by.vorakh.alex.spring_company.model;

public class CompanyPayload {
    private Integer id;
    private String name;
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}
