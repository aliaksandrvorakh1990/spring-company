package by.vorakh.alex.spring_company;

import org.hibernate.Session;

import by.vorakh.alex.spring_company.model.entity.Company;
import by.vorakh.alex.spring_company.utility.HibernateUtility;

public class TestDrive {

    public static void main(String[] args) {
	 Session session = HibernateUtility.getSessionFactory().openSession();
	 
	 session.beginTransaction();
	 
	 Company company = new Company(1, "SoftCom");
	 
	 session.save(company);
	 
	 session.getTransaction().commit();
    }

}
