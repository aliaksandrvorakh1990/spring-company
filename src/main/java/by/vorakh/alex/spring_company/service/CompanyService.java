package by.vorakh.alex.spring_company.service;

import by.vorakh.alex.spring_company.model.CompanyPayload;
import by.vorakh.alex.spring_company.repository.CompanyDAO;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyService implements ServiceInterface<Company, CompanyPayload> {
    
    @Autowired
    private CompanyDAO companyDAO;
    
    private EmployeeDAO employeeDAO;

    @Override
    public List<Company> getAll() {
        return companyDAO.getAll();
    }

    @Override
    public Company getById(int id) {
        return companyDAO.getById(id);
    }

    @Override
    @Transactional
    public void create(CompanyPayload newPayload) {
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
        companyDAO.delete(deletedCompany);
    }

}
