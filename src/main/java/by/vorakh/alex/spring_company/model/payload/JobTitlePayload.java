package by.vorakh.alex.spring_company.model.payload;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The job title details for that creating and updating in the database.")
public class JobTitlePayload {
    
    @PositiveOrZero
    @ApiModelProperty(value = "The job title ID should be unique and is required for updating in the database.",  
    	    example = "32")
    private int id;
    @NotNull(message = "The job title cannot be null")
    @Min(value = 2, message = "The job title does not have to contain less than 2 letters.")
    @Max(value = 20, message = "The job title does not have to contain be greater than 20 letters.")
    @Size(min = 2, max = 20)
    @ApiModelProperty(value = "The job title should be unique and contain 2-20 letters.", 
    	    required = true, example = "CEO")
    private String title;

    public JobTitlePayload() {}

    public JobTitlePayload(@PositiveOrZero int id, @NotNull @Size(min = 2, max = 20) String title) {
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
