package by.vorakh.alex.spring_company.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import by.vorakh.alex.spring_company.model.view_model.PersonalDataViewModel;
import by.vorakh.alex.spring_company.repository.entity.PersonalData;

@Component
public final class PersonalDataToPersonalDataViewModelConverter
	implements Converter<PersonalData, PersonalDataViewModel> {
    @Override
    public PersonalDataViewModel convert(PersonalData source) {
	return new PersonalDataViewModel(source.getId(), source.getFirstName(), source.getLastName());
    }
}
