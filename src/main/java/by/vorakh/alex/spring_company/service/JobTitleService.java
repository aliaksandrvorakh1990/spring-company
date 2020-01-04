package by.vorakh.alex.spring_company.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.model.payload.JobTitlePayload;
import by.vorakh.alex.spring_company.repository.EmployeeDAO;
import by.vorakh.alex.spring_company.repository.JobTitleDAO;
import by.vorakh.alex.spring_company.repository.entity.JobTitle;

@Service
public class JobTitleService implements ServiceInterface<JobTitle, JobTitlePayload> {

    @Autowired
    private JobTitleDAO jobTitleDAO;
    
    @Autowired
    private EmployeeDAO employeeDAO;
        
    @Override
    public List<JobTitle> getAll() {
	return jobTitleDAO.getAll();
    }

    @Override
    public JobTitle getById(int id) {
	return jobTitleDAO.getById(id);
    }

    @Override
    @Transactional
    public void create(JobTitlePayload newPayload) {
	jobTitleDAO.create(new JobTitle(newPayload.getTitle()));
    }

    @Override
    @Transactional
    public void update(int id, JobTitlePayload editedPayload) {
	JobTitle editedJobTitle = jobTitleDAO.getById(id);
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
