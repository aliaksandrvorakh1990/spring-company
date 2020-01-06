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

}
