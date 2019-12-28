package by.vorakh.alex.spring_company.service;

import java.util.List;

public interface ServiceInterface<E,P> {
    List<E> getAll();

    E getById(int id);

    void create(P newPayload);

    void update(int id, P editedPayload);

    void delete(int id);
}
