package by.vorakh.alex.spring_company.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import by.vorakh.alex.spring_company.model.outsource.SkillOutsource;
import by.vorakh.alex.spring_company.repository.entity.Skill;

@Component
public class SkillOutsourceToSkillConverter implements Converter<SkillOutsource,Skill>{

    @Override
    public Skill convert(SkillOutsource source) {
	return new Skill(source.getSkillName());
    }

}
