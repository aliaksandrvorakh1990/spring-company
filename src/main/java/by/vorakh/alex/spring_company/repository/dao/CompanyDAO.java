package by.vorakh.alex.spring_company.repository.dao;

import java.util.List;

import org.hibernate.Session;

import by.vorakh.alex.spring_company.model.entity.Company;
import by.vorakh.alex.spring_company.utility.HibernateUtility;

public class CompanyDAO implements DAO<Company>{

    @SuppressWarnings("unchecked")
    @Override
    public List<Company> getAll() {
	Session session = HibernateUtility.getSessionFactory().openSession(); 
	List<Company> list =session.createQuery("from Company").list();
	session.close();
	return list;
    }

    @Override
    public Company getById(int id) {
	Session session = HibernateUtility.getSessionFactory().openSession(); 
	Company company = (Company) session.load(Company.class, id); 
	session.close();
	return company;
    }

    @Override
    public void create(Company object) {
	Session session = HibernateUtility.getSessionFactory().openSession(); 
	session.save(object); 
	session.close();
    }

    @Override
    public void update(Company object) {
	Session session = HibernateUtility.getSessionFactory().openSession(); 
	session.saveOrUpdate(object); 
	session.close();
    }

    @Override
    public void delete(Company object) {
	Session session = HibernateUtility.getSessionFactory().openSession(); 
	session.delete(object);
	session.close();
    }
    
    public static void main(String[] args) {
	System.out.println(new CompanyDAO().getById(1));
    }

}
