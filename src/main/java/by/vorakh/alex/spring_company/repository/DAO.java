package by.vorakh.alex.spring_company.repository;

import java.util.List;

public interface DAO<V> {
    List<V> getAll();

    V getById(int id);

    int create(V entity);

    void update(V entity);

    void delete(V entity);
    
    boolean isContained(V entity);
    
    V findExisted(V entity);
    
    V createAndGet(V entity);
}
