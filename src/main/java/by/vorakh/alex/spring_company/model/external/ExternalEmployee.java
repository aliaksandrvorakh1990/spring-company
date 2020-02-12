package by.vorakh.alex.spring_company.model.external;

import java.io.Serializable;
import java.util.List;

public class ExternalEmployee implements Serializable {
    private static final long serialVersionUID = 1L;

    private ExternalPersonalData personalData;
    private ExternalJobTitle jobTitle;

    private List<ExternalSkill> skills;

    public ExternalEmployee() {}

    public ExternalEmployee(ExternalPersonalData personalData,
            ExternalJobTitle jobTitle,
            List<ExternalSkill> skills) {
        this.personalData = personalData;
        this.jobTitle = jobTitle;
        this.skills = skills;
    }

    public ExternalEmployee setPersonalData(ExternalPersonalData personalData) {
        this.personalData = personalData;
        return this;
    }

    public ExternalEmployee setJobTitle(ExternalJobTitle jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public ExternalEmployee setSkills(List<ExternalSkill> skills) {
        this.skills = skills;
        return this;
    }

    public ExternalPersonalData getPersonalData() {
        return personalData;
    }

    public ExternalJobTitle getJobTitle() {
        return jobTitle;
    }

    public List<ExternalSkill> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return String.format("ExternalJobTitle[personalData=%s, jobTitle=%s," +
                "skills=%s]", personalData, jobTitle, skills);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((jobTitle == null) 
                ? 0 
                : jobTitle.hashCode());
        result = prime * result + ((personalData == null) 
                ? 0 
                : personalData.hashCode());
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
        ExternalEmployee other = (ExternalEmployee) obj;

        if (jobTitle == null) {
            if (other.jobTitle != null) {
                return false;
            }
        } else if (!jobTitle.equals(other.jobTitle)) {
            return false;
        }
        if (personalData == null) {
            if (other.personalData != null) {
                return false;
            }
        } else if (!personalData.equals(other.personalData)) {
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