package by.vorakh.alex.spring_company.converter;

public interface Converter<E, VM> {
    
    VM converte(E entity);
}
