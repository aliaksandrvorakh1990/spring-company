package by.vorakh.alex.spring_company.service;

import java.util.List;

public interface ServiceInterface<V> {
    List<V> getAll();

    V getById(int id);

    void create(V object);

    void update(V object);

    void delete(int id);
}
