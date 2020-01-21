package by.vorakh.alex.spring_company.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import by.vorakh.alex.spring_company.model.outsource.PersonalDataOutsource;
import by.vorakh.alex.spring_company.repository.entity.PersonalData;

@Component
public class PersonalDataOutsourceToPersonalDataConverter implements 
	Converter<PersonalDataOutsource, PersonalData> {

    @Override
    public PersonalData convert(PersonalDataOutsource source) {
	return new PersonalData(source.getFirstName(), source.getLastName());
    }

}
