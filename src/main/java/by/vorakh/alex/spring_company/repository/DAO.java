package by.vorakh.alex.spring_company.repository;

import java.util.List;

public interface DAO<V> {
    
    List<V> getAll();

    V getById(int id);

    void create(V object);

    void update(V object);

    void delete(V object);
    
}
