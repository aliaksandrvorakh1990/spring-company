package by.vorakh.alex.spring_company.repository.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import by.vorakh.alex.spring_company.model.Company;
import by.vorakh.alex.spring_company.model.CompanyForm;


@Repository
public class CompanyDAO {
    
    private static final Map<Integer, Company> companyMap = new HashMap<Integer, Company>();
    
    static {
	createDB();
    }
    
    private static void createDB() {
	Company company1 = new Company(1, "EvilCom");
	Company company2 = new Company(2, "MegaComr");
	Company company3 = new Company(3, "SofrCom");
	
	companyMap.put(company1.getId(), company1);
	companyMap.put(company2.getId(), company2);
	companyMap.put(company3.getId(), company3);
    }

    public Integer getMaxEmpId() {
        Set<Integer> keys = companyMap.keySet();
        Integer max = 0;
        for (Integer key : keys) {
            if (key > max) {
                max = key;
            }
        }
        return max;
    }
    
    public List<Company> getAllCompanies() {
	Collection<Company> c = companyMap.values();
	List<Company> companies = new ArrayList<Company>(c);
	
	return companies;
    }
    
    public Company getCompany(Integer id) {
	return companyMap.get(id);
    }
    
    public  Company addCompany(CompanyForm form) {
	Integer id = this.getMaxEmpId() + 1;
	form.setId(id);
	Company newCompany = new Company(form);
	companyMap.put(newCompany.getId(), newCompany);
	
	return newCompany;
    }
    
    public  Company updateCompany(CompanyForm form) {
	Company company = this.getCompany(form.getId());
	
	if (company != null) {
	    company.setName(form.getName());
	}
	
	return company;
    }
    
    public void deleteCompany(Integer id) {
	companyMap.remove(id);
    }
}
