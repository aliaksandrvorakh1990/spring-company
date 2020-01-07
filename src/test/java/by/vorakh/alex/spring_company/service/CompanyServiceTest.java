package by.vorakh.alex.spring_company.service;


import by.vorakh.alex.spring_company.converter.CompanyToCompanyViewModelConverter;
import by.vorakh.alex.spring_company.model.payload.CompanyPayload;
import by.vorakh.alex.spring_company.model.view_model.CompanyViewModel;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.repository.CompanyDAO;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.entity.Company;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CompanyServiceTest {
    
    private static final List<CompanyViewModel> controlList = Lists
	    .list(new CompanyViewModel(1, "FirstCompany", null));
    private static final CompanyViewModel controlResult = new CompanyViewModel(2, "TestCompany", null);
    private static final IdViewModel controlIdViewModel = new IdViewModel(3);

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

        List<Company> companyList = Lists.list(firstCompany);
        
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
	Company testCompany = new Company(3, "TestCompany", null);
	CompanyPayload testCompanyPayload = new CompanyPayload(3, "TestCompany", null);
	when(employeeDAO.getAll(testCompanyPayload.getEmployeeIdList())).thenReturn(null);
	when(companyDAO.create(testCompany)).thenReturn(3);
	
	System.out.println(service.create(testCompanyPayload));
	assertEquals(controlIdViewModel, service.create(testCompanyPayload));
    
    }

}