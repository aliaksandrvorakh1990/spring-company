package by.vorakh.alex.spring_company.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.model.JobTitlePayload;
import by.vorakh.alex.spring_company.repository.JobTitleDAO;
import by.vorakh.alex.spring_company.repository.entity.JobTitle;

@Service
public class JobTitleService implements ServiceInterface<JobTitle, JobTitlePayload> {

    @Autowired
    private JobTitleDAO jobTitleDAO;
    
    @Override
    public List<JobTitle> getAll() {
	return jobTitleDAO.getAll();
    }

    @Override
    public JobTitle getById(int id) {
	return jobTitleDAO.getById(id);
    }

    @Transactional
    public void create(JobTitle object) {
	jobTitleDAO.create(object);
    }

    @Transactional
    public void update(JobTitle object) {
	jobTitleDAO.update(object);
    }

    @Override
    @Transactional
    public void delete(int id) {
	JobTitle deletedJobTitle = jobTitleDAO.getById(id);
	jobTitleDAO.delete(deletedJobTitle);
    }

    @Override
    public void create(JobTitlePayload newPayload) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void update(int id, JobTitlePayload editedPayload) {
	// TODO Auto-generated method stub
	
    }

    

}