package by.vorakh.alex.spring_company.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import by.vorakh.alex.spring_company.model.external.ExternalSkill;
import by.vorakh.alex.spring_company.repository.entity.Skill;

@Component
public class SkillOutsourceToSkillConverter implements Converter<ExternalSkill,Skill>{
    @Override
    public Skill convert(ExternalSkill source) {
	return new Skill(source.getSkillName());
    }
}
