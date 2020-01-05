package by.vorakh.alex.spring_company.model.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The job title details for that creating and updating in the database.")
public class JobTitlePayload {
    
    @ApiModelProperty(notes = "The job title ID in the database.")
    private int id;
    @NotNull
    @Size(min = 2, max = 20)
    @ApiModelProperty(notes = "The job title name has to have a length between 2 and 20 letters.")
    private String title;

    public JobTitlePayload() {}

    public JobTitlePayload(int id, @NotNull @Size(min = 2, max = 20) String title) {
	this.id = id;
	this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
