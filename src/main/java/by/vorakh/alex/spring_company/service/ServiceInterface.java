package by.vorakh.alex.spring_company.service;

import java.util.List;

public interface ServiceInterface<VM,P> {
    List<VM> getAll();

    VM getById(int id);

    void create(P newPayload);

    void update(int id, P editedPayload);

    void delete(int id);
}
