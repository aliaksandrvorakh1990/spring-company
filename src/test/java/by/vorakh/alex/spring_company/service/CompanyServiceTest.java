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
    private CompanyDAO companyDAO;
   
    @Mock
    private CompanyToCompanyViewModelConverter companyConvertor;
 
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
	
	when(companyConvertor.convert(firstCompany)).thenCallRealMethod();
	when(companyDAO.getAll()).thenReturn(companyList);
	
	List<CompanyViewModel> resultList = service.getAll();
	assertEquals(1, resultList.size());
    }

}
