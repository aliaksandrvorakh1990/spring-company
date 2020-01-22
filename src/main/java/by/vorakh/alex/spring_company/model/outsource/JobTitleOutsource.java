package by.vorakh.alex.spring_company.model.outsource;

import java.io.Serializable;

public class JobTitleOutsource implements  Serializable {

    private static final long serialVersionUID = 1L;
    
    private String title;
    
    public JobTitleOutsource() {}
    
    public JobTitleOutsource(String title) {
	this.title = title;
    }
    
    public JobTitleOutsource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
	return "JobTitleOutsource[title=" + title + "]";
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
	JobTitleOutsource other = (JobTitleOutsource) obj;
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
