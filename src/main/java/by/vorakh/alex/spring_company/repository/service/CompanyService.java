package by.vorakh.alex.spring_company.repository.service;

import java.util.List;

import by.vorakh.alex.spring_company.model.entity.Company;
import by.vorakh.alex.spring_company.repository.dao.CompanyDAO;

public class CompanyService implements Service<Company>{
    
    private CompanyDAO companyDAO = new CompanyDAO();
    
    @Override
    public List<Company> getAll() {
	return companyDAO.getAll();
    }

    @Override
    public Company getById(int id) {
	return companyDAO.getById(id);
    }

    @Override
    public void create(Company object) {
	companyDAO.create(object);
	
    }

    @Override
    public void update(Company object) {
	companyDAO.update(object);	
    }

    @Override
    public void delete(int id) {
	companyDAO.delete(id);
    }

}
