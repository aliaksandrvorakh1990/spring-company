package by.vorakh.alex.spring_company.model;

public class JobTitlePayload {
    private String title;

    public JobTitlePayload() {}
    
    public JobTitlePayload(String title) {
	this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }

    
    
}
