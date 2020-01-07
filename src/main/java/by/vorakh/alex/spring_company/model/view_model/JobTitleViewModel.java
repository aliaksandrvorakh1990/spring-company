package by.vorakh.alex.spring_company.model.view_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about the job title of an employee for view")
public class JobTitleViewModel {
    
    @ApiModelProperty(value = "The job title ID in the database.", example = "7")
    private int id;
    @ApiModelProperty(value = "The job title name.", example = "Java Developer")
    private String title;
    
    public JobTitleViewModel() {}
    
    public JobTitleViewModel(int id, String title) {
	this.id = id;
	this.title = title;
    }
    
    public JobTitleViewModel setId(int id) {
        this.id = id;
        return this;
    }

    public JobTitleViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
	return "JobTitleViewModel [id=" + id + ", title=" + title + "]";
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
	JobTitleViewModel other = (JobTitleViewModel) obj;
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
