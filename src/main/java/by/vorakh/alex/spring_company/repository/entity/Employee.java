package by.vorakh.alex.spring_company.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private int id;
    
    @NotNull
    @OneToOne
    @JoinColumn(name="personal_data_id")
    private PersonalData personalData;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="title_id")
    private JobTitle jobTitle;

    @ManyToMany
    @JoinTable(name = "employee_to_skill", 
            joinColumns = { @JoinColumn(name = "employee_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "skill_id") })
    private List<Skill> skillList = new ArrayList<Skill>();
    
    public Employee() {}
  
    public Employee(int id, @NotNull PersonalData personalData, @NotNull JobTitle jobTitle, List<Skill> skillList) {
	this.id = id;
	this.personalData = personalData;
	this.jobTitle = jobTitle;
	this.skillList = skillList;
    }

    public Employee setId(int id) {
        this.id = id;
        return this;
    }

    public Employee setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
        return this;
    }
    
    public Employee setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public Employee setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
        return this;
    }

    public int getId() {
        return id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }
    
    public List<Skill> getSkillList() {
        return skillList;
    }
}
