package by.vorakh.alex.spring_company.model.view_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The created object's ID.")
public class IdViewModel {
    
    @ApiModelProperty(notes = "The ID is an object that was created in the database.")
    private int id;
    
    public IdViewModel() {}

    public IdViewModel(int id) {
	this.id = id;
    }
    
    public IdViewModel setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

}
