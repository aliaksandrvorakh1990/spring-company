package by.vorakh.alex.spring_company.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.vorakh.alex.spring_company.model.NewPayloadEntityInterface;
import by.vorakh.alex.spring_company.model.UpdatePyaloadEntityInterface;
import by.vorakh.alex.spring_company.model.JobTitlePayload;
import by.vorakh.alex.spring_company.repository.JobTitleDAO;
import by.vorakh.alex.spring_company.repository.entity.JobTitle;

@Service
public class JobTitleService implements ServiceInterface<JobTitle, JobTitlePayload> {

    @Autowired
    private JobTitleDAO jobTitleDAO;
    
    private NewPayloadEntityInterface<JobTitle, JobTitlePayload>  newJobTitleEntity = (payload) -> {
	return new JobTitle(payload);
    };
    
    private UpdatePyaloadEntityInterface<JobTitle, JobTitlePayload> editedJobTitleEntity = (id, payload) -> {
	JobTitle editedJobTitle = jobTitleDAO.getById(id);
	editedJobTitle.setTitle(payload);
	return editedJobTitle;
    };
    
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
	jobTitleDAO.create(newJobTitleEntity.build(newPayload));
    }

    @Override
    @Transactional
    public void update(int id, JobTitlePayload editedPayload) {
	jobTitleDAO.update(editedJobTitleEntity.edit(id, editedPayload));
    }
   
    @Override
    @Transactional
    public void delete(int id) {
	JobTitle deletedJobTitle = jobTitleDAO.getById(id);
	jobTitleDAO.delete(deletedJobTitle);
    }

}
