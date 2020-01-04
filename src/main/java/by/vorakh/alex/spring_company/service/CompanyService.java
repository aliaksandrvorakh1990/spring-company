package by.vorakh.alex.spring_company.service;

import static by.vorakh.alex.spring_company.model.EntityToViewModelConverter.*;

import by.vorakh.alex.spring_company.model.CompanyPayload;
import by.vorakh.alex.spring_company.model.CompanyViewModel;
import by.vorakh.alex.spring_company.repository.CompanyDAO;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.entity.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService implements ServiceInterface<CompanyViewModel, CompanyPayload> {
    
    @Autowired
    private CompanyDAO companyDAO;
    
    @Autowired
    private EmployeeDAO employeeDAO;
    
    @Override
    public List<CompanyViewModel> getAll() {
	List<CompanyViewModel> companyViewModelList = new ArrayList<CompanyViewModel>();
	companyDAO.getAll().forEach(company -> 
		companyViewModelList.add(converteEntity(company)));
	
        return companyViewModelList;
    }

    @Override
    public CompanyViewModel getById(int id) {
        return converteEntity(companyDAO.getById(id));
    }

    @Override
    @Transactional
    public void create(CompanyPayload newPayload) {
	System.out.println(newPayload.getEmployeeIdList());
	System.out.println(employeeDAO.getAll(newPayload.getEmployeeIdList()));
	companyDAO.create(new Company()
		.setName(newPayload.getName())
		.setEmployeeList(employeeDAO.getAll(newPayload.getEmployeeIdList())));
    }

    @Override
    @Transactional
    public void update(int id, CompanyPayload editedPayload) {
	Company editedCompany = companyDAO.getById(id);
	editedCompany.setName(editedPayload.getName())
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

    

}
