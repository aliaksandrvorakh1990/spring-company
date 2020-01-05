package by.vorakh.alex.spring_company.service;

import by.vorakh.alex.spring_company.converter.EntityToViewModelConverter;
import by.vorakh.alex.spring_company.model.payload.CompanyPayload;
import by.vorakh.alex.spring_company.model.view_model.CompanyViewModel;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
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
    @Autowired
    private EntityToViewModelConverter convertor;
    
    @Override
    public List<CompanyViewModel> getAll() {
	List<CompanyViewModel> companyViewModelList = new ArrayList<CompanyViewModel>();
	companyDAO.getAll().forEach(company -> 
		companyViewModelList.add(convertor.converte(company)));
	
        return companyViewModelList;
    }

    @Override
    public CompanyViewModel getById(int id) {
        return convertor.converte(companyDAO.getById(id));
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

}
