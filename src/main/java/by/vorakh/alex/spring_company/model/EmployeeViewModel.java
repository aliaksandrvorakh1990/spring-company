package by.vorakh.alex.spring_company.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeViewModel {
    
    private int id;
    private PersonalDataViewModel personalData;
    private JobTitleViewModel jobTitle;
    private List<EmployeeViewModel> skillList = new ArrayList<EmployeeViewModel>();
    
    public EmployeeViewModel() {}
    
    public EmployeeViewModel(int id, PersonalDataViewModel personalData, JobTitleViewModel jobTitle,
	    List<EmployeeViewModel> skillList) {
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

    public List<EmployeeViewModel> getSkillList() {
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

    public EmployeeViewModel setSkillList(List<EmployeeViewModel> skillList) {
        this.skillList = skillList;
        return this;
    }
       
}
