package by.vorakh.alex.spring_company.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeViewModel {
    
    private int id;
    private PersonalDataViewModel personalData;
    private JobTitleViewModel jobTitle;
    private List<SkillViewModel> skillList = new ArrayList<SkillViewModel>();
    
    public EmployeeViewModel() {}
    
    public EmployeeViewModel(int id, PersonalDataViewModel personalData, JobTitleViewModel jobTitle,
	    List<SkillViewModel> skillList) {
	this.id = id;
	this.personalData = personalData;
	this.jobTitle = jobTitle;
	this.skillList = skillList;
    }

    public int getId() {
        return id;
    }

    public PersonalDataViewModel getPersonalData() {
        return personalData;
    }

    public JobTitleViewModel getJobTitle() {
        return jobTitle;
    }

    public List<SkillViewModel> getSkillList() {
        return skillList;
    }

    public EmployeeViewModel setId(int id) {
        this.id = id;
        return this;
    }

    public EmployeeViewModel setPersonalData(PersonalDataViewModel personalData) {
        this.personalData = personalData;
        return this;
    }

    public EmployeeViewModel setJobTitle(JobTitleViewModel jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public EmployeeViewModel setSkillList(List<SkillViewModel> skillList) {
        this.skillList = skillList;
        return this;
    }
       
}
