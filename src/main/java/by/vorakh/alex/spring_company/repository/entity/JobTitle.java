package by.vorakh.alex.spring_company.repository.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="job_title")
public class JobTitle {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "title_id")
    private int id;
    
    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "title", length=20, nullable=false)
    private String title;
    
    public JobTitle() {}
    
    public JobTitle(int id, String title) {
	this.id = id;
	this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
	return "JobTitle [id=" + id + ", title=" + title + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	result = prime * result + ((title == null) ? 0 : title.hashCode());
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
	JobTitle other = (JobTitle) obj;
	if (id != other.id) {
	    return false;
	}
	if (title == null) {
	    if (other.title != null) {
		return false;
	    }
	} else if (!title.equals(other.title)) {
	    return false;
	}   
	return true;
    }
    
}