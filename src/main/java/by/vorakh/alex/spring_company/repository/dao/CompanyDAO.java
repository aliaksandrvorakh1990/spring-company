package by.vorakh.alex.spring_company.repository.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import by.vorakh.alex.spring_company.model.entity.Company;
import by.vorakh.alex.spring_company.utility.HibernateUtility;

public class CompanyDAO implements DAO<Company>{

    @Override
    public List<Company> getAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Company getById(int id) {
	Session session = HibernateUtility.getSessionFactory().openSession(); 
	session.beginTransaction();
	Company company = (Company) session.load(Company.class, id); 
	Hibernate.initialize(company.getSubjectList());
	session.getTransaction().commit();

	return null;
    }

    @Override
    public void create(Company object) {
	Session session = HibernateUtility.getSessionFactory().openSession(); 
	session.beginTransaction();
	session.save(object); 
	session.getTransaction().commit();
    }

    @Override
    public void update(Company object) {
	Session session = HibernateUtility.getSessionFactory().openSession(); 
	session.beginTransaction();
	session.saveOrUpdate(object); 
	session.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
	Session session = HibernateUtility.getSessionFactory().openSession(); 
	session.beginTransaction();
	Company company = (Company) session.load(Company.class, id); 
	session.delete(company); 
	session.getTransaction().commit();
    }

}
