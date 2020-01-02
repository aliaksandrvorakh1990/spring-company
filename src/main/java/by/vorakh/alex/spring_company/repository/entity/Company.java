package by.vorakh.alex.spring_company.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "company")
public class Company  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id", unique = true, nullable = false)
    private Integer id;

    @NotNull
    @Size(min = 2, max = 40)
    @Column(name = "name", length = 40, nullable = false, unique = true)
    private String name;
    
    @OneToMany
    @JoinColumn(name = "company_id")
    private List<Employee> employeeList = new ArrayList<Employee>();

    public Company() {}

    public Company(Integer id, @NotNull @Size(min = 2, max = 40) String name, List<Employee> employeeList) {
	this.id = id;
	this.name = name;
	this.employeeList = employeeList;
    }
    

    public Company setId(Integer id) {
        this.id = id;
        return this;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public Company setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
        return this;
    }
    
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
    
    @Override
    public String toString() {
	return "Company [id=" + id + ", name=" + name + ", employeeList=" + employeeList + "]";
    }
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((employeeList == null) ? 0 : employeeList.hashCode());
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

	Company other = (Company) obj;
	if (id == null) {
	    if (other.id != null) {
		return false;
	    }
	} else if (!id.equals(other.id)) {
	    return false;
	}
	if (name == null) {
	    if (other.name != null) {
		return false;
	    }
	} else if (!name.equals(other.name)) {
	    return false;
	}
	if (employeeList == null) {
	    if (other.employeeList != null) {
		return false;
	    }
	} else if (!employeeList.equals(other.employeeList)) {
	    return false;
	}
	   
	return true;
    }

}