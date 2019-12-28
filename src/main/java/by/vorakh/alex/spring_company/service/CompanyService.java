package by.vorakh.alex.spring_company.service;

import by.vorakh.alex.spring_company.model.CompanyPayload;
import by.vorakh.alex.spring_company.repository.CompanyDAO;
import by.vorakh.alex.spring_company.repository.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyService implements ServiceInterface<Company, CompanyPayload> {
    
    @Autowired
    private CompanyDAO companyDAO;

    @Override
    public List<Company> getAll() {
        return companyDAO.getAll();
    }

    @Override
    public Company getById(int id) {
        return companyDAO.getById(id);
    }

    @Transactional
    public void create(Company object) {
        companyDAO.create(object);
    }
    
    @Override
    public void create(CompanyPayload newPayload) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void update(int id, CompanyPayload editedPayload) {
	// TODO Auto-generated method stub
	
    }

    //@Override
    @Transactional
    public void update(Company object) {
        companyDAO.update(object);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Company deletedCompany = companyDAO.getById(id);
        companyDAO.delete(deletedCompany);
    }

}
