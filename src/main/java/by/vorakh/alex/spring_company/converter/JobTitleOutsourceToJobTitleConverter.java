package by.vorakh.alex.spring_company.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import by.vorakh.alex.spring_company.model.external.ExternalJobTitle;
import by.vorakh.alex.spring_company.repository.entity.JobTitle;

@Component
public class JobTitleOutsourceToJobTitleConverter implements 
        Converter<ExternalJobTitle, JobTitle> {
    @Override
    public JobTitle convert(ExternalJobTitle source) {
        return new JobTitle(source.getTitle());
    }
}
