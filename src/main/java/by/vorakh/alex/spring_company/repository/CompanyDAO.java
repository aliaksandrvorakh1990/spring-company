package by.vorakh.alex.spring_company.repository;

import by.vorakh.alex.spring_company.repository.entity.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDAO implements DAO<Company> {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Company> getAll() {
        Session session = sessionFactory.openSession();
        List<Company> list = session.createQuery("from Company").list();
        session.close();
        return list;
    }

    @Override
    public Company getById(int id) {
        Session session = sessionFactory.openSession();

        return session.get(Company.class, id);
    }

    @Override
    public void create(Company object) {
        Session session = sessionFactory.openSession();
        session.save(object);
        session.close();
    }

    @Override
    public void update(Company object) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(object);
        session.close();
    }

    @Override
    public void delete(Company object) {
        Session session = sessionFactory.openSession();
        session.delete(object);
        session.close();
    }

}
