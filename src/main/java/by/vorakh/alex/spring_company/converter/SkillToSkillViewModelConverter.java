package by.vorakh.alex.spring_company.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import by.vorakh.alex.spring_company.model.view_model.SkillViewModel;
import by.vorakh.alex.spring_company.repository.entity.Skill;

@Component
public final class SkillToSkillViewModelConverter implements 
        Converter<Skill, SkillViewModel> {
    @Override
    public SkillViewModel convert(Skill source) {
        return new SkillViewModel(source.getId(), source.getName());
    }
}
