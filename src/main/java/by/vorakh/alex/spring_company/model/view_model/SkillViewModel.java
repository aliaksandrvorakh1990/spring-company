package by.vorakh.alex.spring_company.model.view_model;

public class SkillViewModel {
    private int id;
    private String skillName;
    
    public SkillViewModel() {}
    
    public SkillViewModel(int id, String skillName) {
	this.id = id;
	this.skillName = skillName;
    }
    
    public SkillViewModel setId(int id) {
        this.id = id;
        return this;
    }

    public SkillViewModel setSkillName(String skillName) {
        this.skillName = skillName;
        return this;
    }
    
    public int getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }

}
