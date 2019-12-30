package by.vorakh.alex.spring_company.model;

public interface NewPayloadEntityInterface<E,P> {
    
    E build(P payload); 
}
