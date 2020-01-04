package by.vorakh.alex.spring_company.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import by.vorakh.alex.spring_company.model.view_model.CompanyViewModel;
import by.vorakh.alex.spring_company.model.view_model.EmployeeViewModel;
import by.vorakh.alex.spring_company.model.view_model.JobTitleViewModel;
import by.vorakh.alex.spring_company.model.view_model.PersonalDataViewModel;
import by.vorakh.alex.spring_company.model.view_model.SkillViewModel;
import by.vorakh.alex.spring_company.repository.entity.Company;
import by.vorakh.alex.spring_company.repository.entity.Employee;
import by.vorakh.alex.spring_company.repository.entity.JobTitle;
import by.vorakh.alex.spring_company.repository.entity.PersonalData;
import by.vorakh.alex.spring_company.repository.entity.Skill;

@Component
public class EntityToViewModelConverter {
    
    private Converter<Employee, EmployeeViewModel> employeeConverter = (employee) -> {
	return new EmployeeViewModel(employee.getId(), converte(employee.getPersonalData()),
		converte(employee.getJobTitle()), converteSkillList(employee.getSkillList()));
    };
    
    private Converter<Company, CompanyViewModel> companyConverter = (company) -> {
	return new CompanyViewModel(company.getId(), company.getName(), converteEmployeeList(company.getEmployeeList()));
    };
    
    private Converter<JobTitle, JobTitleViewModel> jobTitleConverter = (jobTitle) -> {
	return new JobTitleViewModel(jobTitle.getId(), jobTitle.getTitle());
    };
    
    private Converter<PersonalData,PersonalDataViewModel> personalDataConverter = (personalData) -> {
   	return new PersonalDataViewModel(personalData.getId(), personalData.getFirstName(),
   		personalData.getLastName());
    };
    
    private Converter<Skill,SkillViewModel> skillConverter = (skill) -> {
   	return new SkillViewModel(skill.getId(), skill.getSkillName());
    };
    
    public EntityToViewModelConverter() {}
    
    private List<SkillViewModel> converteSkillList(List<Skill> skillList) {
	List<SkillViewModel> skillViewModelList = new ArrayList<SkillViewModel>();
	
	if (skillList != null) {
	    if (!skillList.isEmpty()) {
		skillList.forEach(skill -> {
		    skillViewModelList.add(skillConverter.converte(skill));
		});
	    }
	}
	
	return skillViewModelList;
    }
     
    private List<EmployeeViewModel> converteEmployeeList(List<Employee> employeeList) {
	List<EmployeeViewModel> skillViewModelList = new ArrayList<EmployeeViewModel>();
	
	if (employeeList != null) {
	    if (!employeeList.isEmpty()) {
		employeeList.forEach(employee -> {
		    skillViewModelList.add(employeeConverter.converte(employee));
		});
	    }
	}
	
	return skillViewModelList;
    }
    
    public JobTitleViewModel converte(JobTitle jobTitle) {
	return jobTitleConverter.converte(jobTitle);
    }
    
    public PersonalDataViewModel converte(PersonalData personalData) {
	return personalDataConverter.converte(personalData);
    }
    
    public SkillViewModel converte(Skill skill) {
	return skillConverter.converte(skill);
    }
    
    public EmployeeViewModel converte(Employee employee) {
	return employeeConverter.converte(employee);
    }
    
    public CompanyViewModel converte(Company company) {
	return companyConverter.converte(company);
    }
    
}
