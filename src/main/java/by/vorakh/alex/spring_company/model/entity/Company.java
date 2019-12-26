package by.vorakh.alex.spring_company.model.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="company")
public class Company  {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id")
    private Integer id;
    
    @NotNull
    @Size(min = 2, max = 40)
    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "name", length=40, nullable=false, unique=true)
    private String name;
    
    public Company() {}
    
    public Company(Integer id, String name) {
	this.id = id;
	this.name = name;
    }

    public Company setId(int id) {
        this.id = id;
        return this;
    }
    
    public Company setName(String name) {
        this.name = name;
        return this;
    }
    	
    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "Company[id=" + id + ", name=" + name + "]";
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        return true;
    }
}
