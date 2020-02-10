package by.vorakh.alex.spring_company.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import by.vorakh.alex.spring_company.converter.CompanyToCompanyViewModelConverter;
import by.vorakh.alex.spring_company.model.payload.CompanyPayload;
import by.vorakh.alex.spring_company.model.view_model.CompanyViewModel;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.repository.CompanyDAO;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.entity.Company;
import by.vorakh.alex.spring_company.repository.entity.Employee;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Sets;

import java.util.List;

public class CompanyServiceTest {
    
    
    private static final List<CompanyViewModel> controlList = Lists.newArrayList(new CompanyViewModel(1, "FirstCompany", null));
    private static final CompanyViewModel controlResult = new CompanyViewModel(2, "TestCompany", null);
    private static IdViewModel controlIdViewModel = new IdViewModel(3);

    @Mock
    private CompanyToCompanyViewModelConverter companyConverter;

    @Mock
    private CompanyDAO companyDAO;
    @Mock
    private EmployeeDAO employeeDAO;
    @InjectMocks
    private CompanyService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() {
        Company firstCompany = new Company(1, "FirstCompany", null);
        CompanyViewModel companyViewModel = new CompanyViewModel(1, "FirstCompany", null);

        List<Company> companyList = Lists.newArrayList(firstCompany);
        
        when(companyConverter.convert(firstCompany)).thenReturn(companyViewModel);
        when(companyDAO.getAll()).thenReturn(companyList);

        List<CompanyViewModel> resultList = service.getAll();
       
        assertEquals(1, resultList.size());
        assertEquals(controlList, resultList);
    }
    
    @Test
    public void testGetById() {
	 Company company = new Company(2, "TestCompany", null);
	 CompanyViewModel companyViewModel = new CompanyViewModel(2, "TestCompany", null);
	 
	 when(companyConverter.convert(company)).thenReturn(companyViewModel);
	 when(companyDAO.getById(2)).thenReturn(company);
	 
	 assertEquals(controlResult,service.getById(2));
    }
     
    @Test
    public void testCreate() {
         CompanyPayload testCompanyPayload = new CompanyPayload(3, "TestCompany", null);
         when(employeeDAO.getAll(testCompanyPayload.getEmployeeIdList())).thenReturn(null);
         doReturn(3).when(companyDAO).create(any(Company.class));
         assertEquals(controlIdViewModel, service.create(testCompanyPayload));
    }
    
    @Test
    public void testUpdate() {
	CompanyPayload testCompanyPayload = new CompanyPayload(3, "TestCompany", Lists.newArrayList(4));
	Company company = new Company(3, "OldTestCompany",  Sets.newHashSet(new Employee()));
	when(companyDAO.getById(3)).thenReturn(company);
	//when(employeeDAO.getAll(any(Set.class))).thenReturn(Lists.newArrayList(new Employee()));
	service.update(testCompanyPayload);
	verify(companyDAO, times(1)).update(company);
    }
    
    @Test
    public void testDelete() {
	Company company = new Company(2, "TestCompany", Sets.newHashSet(new Employee()));
	when(companyDAO.getById(2)).thenReturn(company);
	service.delete(2);
	verify(employeeDAO, atLeastOnce()).delete(any(Employee.class));
	verify(companyDAO, times(1)).delete(company);
    }
    
}