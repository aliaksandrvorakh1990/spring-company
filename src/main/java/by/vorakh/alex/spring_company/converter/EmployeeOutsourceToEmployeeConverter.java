package by.vorakh.alex.spring_company.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import by.vorakh.alex.spring_company.model.external.ExternalEmployee;
import by.vorakh.alex.spring_company.repository.entity.Employee;
import by.vorakh.alex.spring_company.repository.entity.Skill;

@Component
public class EmployeeOutsourceToEmployeeConverter implements 
	Converter<ExternalEmployee, Employee> {
    @Autowired
    private PersonalDataOutsourceToPersonalDataConverter personalDataOutsourceConverter;
    @Autowired
    private JobTitleOutsourceToJobTitleConverter jobTitleOutsourceConverter; 
    @Autowired
    private SkillOutsourceToSkillConverter skillOutsourceConverter;
    
    @Override
    public Employee convert(ExternalEmployee source) {
        List<Skill> skillList = new ArrayList<>();
        source.getSkills().forEach(someSkill -> {
            skillList.add(skillOutsourceConverter.convert(someSkill));    
        });
        	
        return new Employee(personalDataOutsourceConverter
                .convert(source.getPersonalData()), 
                      jobTitleOutsourceConverter.convert(source.getJobTitle()), 
                      skillList);
    }
}
