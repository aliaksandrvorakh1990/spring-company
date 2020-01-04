package by.vorakh.alex.spring_company.model.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class JobTitlePayload {
    
    @NotNull
    @Size(min = 2, max = 20)
    private String title;

    public JobTitlePayload() {}
    
    public JobTitlePayload(@NotNull @Size(min = 2, max = 20) String title) {
	this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }

}
