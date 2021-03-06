package by.vorakh.alex.spring_company.service;

import java.util.List;

import by.vorakh.alex.spring_company.model.view_model.IdViewModel;

public interface ServiceInterface<V, P> {
    List<V> getAll();

    V getById(int id);

    IdViewModel create(P newPayload);

    void update(P editedPayload);

    void delete(int id);
}
