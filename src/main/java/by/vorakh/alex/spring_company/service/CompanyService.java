package by.vorakh.alex.spring_company.service;

import by.vorakh.alex.spring_company.repository.CompanyDAO;
import by.vorakh.alex.spring_company.repository.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService implements ServiceInterface<Company> {
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
        Company deletedCompany = companyDAO.getById(id);
        System.out.println(deletedCompany);
        companyDAO.delete(deletedCompany);
    }

}
