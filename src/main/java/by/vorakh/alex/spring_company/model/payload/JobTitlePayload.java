package by.vorakh.alex.spring_company.model.payload;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The job title details for that creating and updating in the database.")
public class JobTitlePayload implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @PositiveOrZero
    @ApiModelProperty(value = "The job title ID should be unique and is required for updating in the database.",  
    	    example = "32")
    private Integer id;
    @NotNull(message = "The job title cannot be null")
    @Size(min = 2, max = 20, message = "The job title should be unique and contain 2-20 letters.")
    @ApiModelProperty(value = "The job title should be unique and contain 2-20 letters.", 
    	    required = true, example = "CEO")
    private String title;

    public JobTitlePayload() {}

    public JobTitlePayload(@PositiveOrZero Integer id, @NotNull @Size(min = 2, max = 20) String title) {
	this.id = id;
	this.title = title;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
