package by.vorakh.alex.spring_company.model.view_model;

public class JobTitleViewModel {
    
    private int id;
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
