package by.vorakh.alex.spring_company.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import by.vorakh.alex.spring_company.model.view_model.JobTitleViewModel;
import by.vorakh.alex.spring_company.repository.entity.JobTitle;

@Component
public final class JobTitleToJobTitleViewModelConverter implements 
	Converter<JobTitle, JobTitleViewModel> {

    @Override
    public JobTitleViewModel convert(JobTitle source) {
	return new JobTitleViewModel(source.getId(), source.getTitle());
    }

}
