package by.vorakh.alex.spring_company.model;

public interface Converter<E, VM> {
    
    VM converte(E entity);
}
