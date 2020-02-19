package by.vorakh.alex.spring_company.repository.entity;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "company")
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id", unique = true, nullable = false)
    private Integer id;

    @NotNull
    @Size(min = 2, max = 40)
    @Column(name = "name", length = 40, nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Set < Employee > employees;

    public Company() {}

    public Company(@NotNull @Size(min = 2, max = 40) String name, 
            Set<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public Company(@NotNull Integer id, 
            @NotNull @Size(min = 2, max = 40) String name, 
            Set<Employee>employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public Company setId(Integer id) {
        this.id = id;
        return this;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public Company setEmployees(Set<Employee> employees) {
        this.employees = employees;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Employee>getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return String.format("Company [id=%s, name=%s, employees=%s]",
                id, name, employees);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return prime * result + ((employees == null) 
                ? 0 
                : employees.hashCode());
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
        if (id != other.id) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (employees == null) {
            if (other.employees != null) {
                return false;
            }
        } else if (!employees.equals(other.employees)) {
            return false;
        }

        return true;
    }
}