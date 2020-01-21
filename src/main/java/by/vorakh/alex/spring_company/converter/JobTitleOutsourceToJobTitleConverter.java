package by.vorakh.alex.spring_company.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import by.vorakh.alex.spring_company.model.outsource.JobTitleOutsource;
import by.vorakh.alex.spring_company.repository.entity.JobTitle;

@Component
public class JobTitleOutsourceToJobTitleConverter implements 
	Converter<JobTitleOutsource, JobTitle> {

    @Override
    public JobTitle convert(JobTitleOutsource source) {
	return new JobTitle(source.getTitle());
    }

}
