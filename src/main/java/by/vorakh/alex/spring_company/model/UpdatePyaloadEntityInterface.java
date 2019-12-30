package by.vorakh.alex.spring_company.model;

public interface UpdatePyaloadEntityInterface<E,P> {
    E edit(int id, P payload);
}
