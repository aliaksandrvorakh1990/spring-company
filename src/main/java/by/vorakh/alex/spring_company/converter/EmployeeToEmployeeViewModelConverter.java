package by.vorakh.alex.spring_company.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import by.vorakh.alex.spring_company.model.view_model.EmployeeViewModel;
import by.vorakh.alex.spring_company.model.view_model.SkillViewModel;
import by.vorakh.alex.spring_company.repository.entity.Employee;

@Component
public final class EmployeeToEmployeeViewModelConverter implements Converter<Employee, EmployeeViewModel> {
    
    @Autowired
    private SkillToSkillViewModelConverter skillConverter;
    @Autowired
    private JobTitleToJobTitleViewModelConverter jobTitleConverter;
    @Autowired
    private PersonalDataToPersonalDataViewModelConverter personalDataConverter; 
    
    @Override
    public EmployeeViewModel convert(Employee source) {
	List<SkillViewModel> skillList = new ArrayList<SkillViewModel>();
	
	source.getSkillList().forEach(skill -> 
		skillList.add(skillConverter.convert(skill)));
	
	return new EmployeeViewModel(
		source.getId(), 
		personalDataConverter.convert(source.getPersonalData()),
		jobTitleConverter.convert(source.getJobTitle()),
		skillList);
    }

}
