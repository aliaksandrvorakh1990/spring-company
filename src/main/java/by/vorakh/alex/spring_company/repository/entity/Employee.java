package by.vorakh.alex.spring_company.repository.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;
    @NotNull
    @OneToOne
    @JoinColumn(name = "personal_data_id")
    private PersonalData personalData;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "title_id")
    private JobTitle jobTitle;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_to_skill",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;

    public Employee() {}

    public Employee(@NotNull PersonalData personalData, 
            @NotNull JobTitle jobTitle,
            @NotNull List < Skill > skills) {
        this.personalData = personalData;
        this.jobTitle = jobTitle;
        this.skills = skills;
    }

    public Employee(@NotNull Integer id, @NotNull PersonalData personalData, 
            @NotNull JobTitle jobTitle,
        List < Skill > skills) {
        this.id = id;
        this.personalData = personalData;
        this.jobTitle = jobTitle;
        this.skills = skills;
    }

    public Employee setId(Integer id) {
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

    public Employee setSkills(List < Skill > skills) {
        this.skills = skills;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public List < Skill > getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return String.format("Employee [id=%s, personalData=%s, jobTitle=%s," +
            " skills=%s]", id, personalData, jobTitle, skills);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((personalData == null) 
                ? 0 
                : personalData.hashCode());
        result = prime * result + ((jobTitle == null) 
                ? 0 
                : jobTitle.hashCode());
        return prime * result + ((skills == null) ? 0 : skills.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Employee other = (Employee) obj;
        if (id != other.id) {
            return false;
        }
        if (personalData == null) {
            if (other.personalData != null) {
                return false;
            }
        } else if (!personalData.equals(other.personalData)) {
            return false;
        }
        if (jobTitle == null) {
            if (other.jobTitle != null) {
                return false;
            }
        } else if (!jobTitle.equals(other.jobTitle)) {
            return false;
        }
        if (skills == null) {
            if (other.skills != null) {
                return false;
            }
        } else if (!skills.equals(other.skills)) {
            return false;
        }
        return true;
    }
}