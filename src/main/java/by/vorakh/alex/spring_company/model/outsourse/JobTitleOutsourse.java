package by.vorakh.alex.spring_company.model.outsourse;

import java.io.Serializable;

public class JobTitleOutsourse implements  Serializable {

    private String title;
    
    public JobTitleOutsourse() {}
    
    public JobTitleOutsourse(String title) {
	this.title = title;
    }
    
    public JobTitleOutsourse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
	return "JobTitleOutsourse[title=" + title + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
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
	JobTitleOutsourse other = (JobTitleOutsourse) obj;
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
