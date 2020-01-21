package by.vorakh.alex.spring_company.service;

import by.vorakh.alex.spring_company.converter.CompanyToCompanyViewModelConverter;
import by.vorakh.alex.spring_company.converter.EmployeeOutsourceToEmployeeConverter;
import by.vorakh.alex.spring_company.model.outsource.EmployeeOutsource;
import by.vorakh.alex.spring_company.model.payload.CompanyPayload;
import by.vorakh.alex.spring_company.model.view_model.CompanyViewModel;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.repository.CompanyDAO;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.JobTitleDAO;
import by.vorakh.alex.spring_company.repository.PersonalDataDAO;
import by.vorakh.alex.spring_company.repository.SkillDAO;
import by.vorakh.alex.spring_company.repository.entity.Company;
import by.vorakh.alex.spring_company.repository.entity.Employee;
import by.vorakh.alex.spring_company.repository.entity.PersonalData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService implements ServiceInterface<CompanyViewModel, CompanyPayload> {
    
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private PersonalDataDAO personalDataDAO;
    @Autowired
    private SkillDAO skillDAO;
    @Autowired
    private JobTitleDAO jobTitleDAO;
    @Autowired
    private CompanyToCompanyViewModelConverter convertor;
    @Autowired
    private EmployeeOutsourceToEmployeeConverter employeeOutsourceToEmployeeConverter;
    
    @Override
    public List<CompanyViewModel> getAll() {
	List<CompanyViewModel> companyViewModelList = new ArrayList<CompanyViewModel>();
	companyDAO.getAll().forEach(company -> 
		companyViewModelList.add(convertor.convert(company)));
	
        return companyViewModelList;
    }

    @Override
    public CompanyViewModel getById(int id) {
        return convertor.convert(companyDAO.getById(id));
    }

    @Override
    @Transactional
    public IdViewModel create(CompanyPayload newPayload) {
	return new IdViewModel()
		.setId(companyDAO.create(new Company()
			.setName(newPayload.getName())
			.setEmployeeList(employeeDAO.getAll(newPayload.getEmployeeIdList()))));
    }

    @Override
    @Transactional
    public void update(CompanyPayload editedPayload) {
	Company editedCompany = companyDAO.getById(editedPayload.getId());
	editedCompany
		.setName(editedPayload.getName())
		.setEmployeeList(employeeDAO.getAll(editedPayload.getEmployeeIdList()));
	companyDAO.update(editedCompany);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Company deletedCompany = companyDAO.getById(id);
        deletedCompany.getEmployeeList().forEach(emp -> {
            emp.getSkillList().clear();
            employeeDAO.delete(emp);
        });
      
        companyDAO.delete(deletedCompany);
    }
    
    @Transactional
    public Company randomEmployee(int companyId,String jobTiTleName) {
	RestTemplate client = new RestTemplate();
	String randomEmployeeURL = "http://localhost:8082/random-employee/"+ jobTiTleName;
	EmployeeOutsource outsourceEmployee = client.getForObject(randomEmployeeURL, EmployeeOutsource.class);
	if (outsourceEmployee == null) {
	    // TODO throw Service exception
	    System.out.println("ERROR No data for an external resource");
	}
	Employee randomEmp = employeeOutsourceToEmployeeConverter.convert(outsourceEmployee);
	
	PersonalData randomPD = randomEmp.getPersonalData();
	
	
	return null;
    }
    
    private Employee AddAndGetToDAO(Employee someEmployee) {
	return null;
    }

}
