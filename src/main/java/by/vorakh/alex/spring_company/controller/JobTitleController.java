package by.vorakh.alex.spring_company.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.vorakh.alex.spring_company.repository.entity.JobTitle;
import by.vorakh.alex.spring_company.service.JobTitleService;

@RestController
@RequestMapping("/project")
public class JobTitleController {
    @Autowired
    private JobTitleService jobTitleService;
    
    @GetMapping("/jobs")
    public List<JobTitle> getJobTitles() {
        return jobTitleService.getAll();
    }

    @GetMapping(value = "/jobs/{id}")
    public JobTitle getJobTitle(@PathVariable("id") Integer id) {
        return jobTitleService.getById(id);
    }

    @PostMapping("/jobs")
    public void createJobTitle(@Valid @RequestBody JobTitle newJobTitle) {
	
	jobTitleService.create(newJobTitle);
    }

    @PutMapping(value = "/jobs/{id}")
    public void updateJobTitle(@PathVariable(value = "id") Integer id,
                              @Valid @RequestBody JobTitle editedJobTitle) {
	JobTitle jobTitle = jobTitleService.getById(id);

        jobTitle.setTitle(editedJobTitle.getTitle());

        jobTitleService.update(jobTitle);
    }

    @DeleteMapping(value = "/jobs/{id}")
    public void deleteJobTitle(@PathVariable("id") Integer id) {
	jobTitleService.delete(id);
    }

}
