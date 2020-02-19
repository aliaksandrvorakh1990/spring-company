package by.vorakh.alex.spring_company.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import by.vorakh.alex.spring_company.model.external.ExternalPersonalData;
import by.vorakh.alex.spring_company.repository.entity.PersonalData;

@Component
public class PersonalDataOutsourceToPersonalDataConverter implements 
        Converter<ExternalPersonalData, PersonalData> {
    @Override
    public PersonalData convert(ExternalPersonalData source) {
        return new PersonalData(source.getFirstName(), source.getLastName());
    }
}
