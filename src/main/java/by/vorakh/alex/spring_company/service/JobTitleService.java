package by.vorakh.alex.spring_company.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.converter.EntityToViewModelConverter;
import by.vorakh.alex.spring_company.model.payload.JobTitlePayload;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.model.view_model.JobTitleViewModel;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.JobTitleDAO;
import by.vorakh.alex.spring_company.repository.entity.JobTitle;

@Service
public class JobTitleService implements ServiceInterface<JobTitleViewModel, JobTitlePayload> {

    @Autowired
    private JobTitleDAO jobTitleDAO;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private EntityToViewModelConverter convertor;
        
    @Override
    public List<JobTitleViewModel> getAll() {
	List<JobTitleViewModel> jobTitleViewModelList = new ArrayList<JobTitleViewModel>();
	jobTitleDAO.getAll().forEach(jobTitle -> {
	    jobTitleViewModelList.add(convertor.converte(jobTitle));
	});
	
	return jobTitleViewModelList;
    }

    @Override
    public JobTitleViewModel getById(int id) {
	return convertor.converte(jobTitleDAO.getById(id));
    }

    @Override
    @Transactional
    public IdViewModel create(JobTitlePayload newPayload) {
	return new IdViewModel()
		.setId(jobTitleDAO.create(new JobTitle(newPayload.getTitle())));
    }

    @Override
    @Transactional
    public void update(JobTitlePayload editedPayload) {
	JobTitle editedJobTitle = jobTitleDAO.getById(editedPayload.getId());
	editedJobTitle.setTitle(editedPayload.getTitle());
	jobTitleDAO.update(editedJobTitle);
    }
   
    @Override
    @Transactional
    public void delete(int id) {
	JobTitle deletedJobTitle = jobTitleDAO.getById(id);
	employeeDAO.delete(deletedJobTitle);
	jobTitleDAO.delete(deletedJobTitle);
    }

}
