package by.vorakh.alex.spring_company.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import by.vorakh.alex.spring_company.converter.CompanyToCompanyViewModelConverter;
import by.vorakh.alex.spring_company.converter.EmployeeToEmployeeViewModelConverter;
import by.vorakh.alex.spring_company.converter.JobTitleToJobTitleViewModelConverter;
import by.vorakh.alex.spring_company.converter.PersonalDataToPersonalDataViewModelConverter;
import by.vorakh.alex.spring_company.converter.SkillToSkillViewModelConverter;
import by.vorakh.alex.spring_company.model.view_model.CompanyViewModel;
import by.vorakh.alex.spring_company.repository.CompanyDAO;
import by.vorakh.alex.spring_company.repository.entity.Company;
import by.vorakh.alex.spring_company.repository.entity.Employee;
import by.vorakh.alex.spring_company.repository.entity.JobTitle;
import by.vorakh.alex.spring_company.repository.entity.PersonalData;
import by.vorakh.alex.spring_company.repository.entity.Skill;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {
    
   
    @Mock
    private SkillToSkillViewModelConverter skillConverter;
    @Mock
    private JobTitleToJobTitleViewModelConverter jobTitleConverter;
    @Mock
    private PersonalDataToPersonalDataViewModelConverter personalDataConverter; 
    @Mock
    private EmployeeToEmployeeViewModelConverter employeeConverter;
    @Mock
    private CompanyToCompanyViewModelConverter companyConvertor;
    @Mock
    private CompanyDAO companyDAO;
    @InjectMocks
    private CompanyService service;
    
    @Test
    public void testGetAll() {
	PersonalData madMax = new PersonalData(1, "Mad", "Max");
	JobTitle ceo = new JobTitle(1, "CEO");
	
	Skill java = new Skill(1, "Java");
	
	List<Skill> ceoSkills = new ArrayList<Skill>();
	ceoSkills.add(java);
	
	Employee madMaxEmp = new Employee(1, madMax, ceo, ceoSkills);
	List<Employee> employeeListOfFirstCompany = new ArrayList<Employee>();
	
	employeeListOfFirstCompany.add(madMaxEmp);
	
	Company firstCompany = new Company(1, "FirstCompany", employeeListOfFirstCompany);
	
	List<Company> companyList = new ArrayList<Company>();
	companyList.add(firstCompany);
	
	when(jobTitleConverter.convert(ceo)).thenCallRealMethod();
	when(skillConverter.convert(java)).thenCallRealMethod();
	when(personalDataConverter.convert(madMax)).thenCallRealMethod();
	when(employeeConverter.convert(madMaxEmp)).thenCallRealMethod();
	when(companyConvertor.convert(firstCompany)).thenCallRealMethod();
	
	when(companyDAO.getAll()).thenReturn(companyList);
	
	List<CompanyViewModel> resultList = service.getAll();
	assertEquals(1, resultList.size());
    }

}
