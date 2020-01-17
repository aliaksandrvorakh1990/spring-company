package by.vorakh.alex.spring_company.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import by.vorakh.alex.spring_company.model.payload.JobTitlePayload;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.model.view_model.JobTitleViewModel;
import by.vorakh.alex.spring_company.service.JobTitleService;

@Api(description="Operations pertaining to job title")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 1800)
@RequestMapping("/project")
public class JobTitleController {
    
    @Autowired
    private JobTitleService jobTitleService;
    
    @ApiOperation(value = "Get a list of existing job titles from the database",
	    response = JobTitleViewModel.class,
	    responseContainer = "List", code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, message = "Problems with server")
	})
    @GetMapping("/jobs")
    public List<JobTitleViewModel> getJobTitles() {
        return jobTitleService.getAll();
    }

    @ApiOperation(value = "Get an job title by Id from the database", 
	    notes = "ID has to be greater than zero.", 
	    response = JobTitleViewModel.class , code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, message = "The job title does not exist or Problems with server")
	})
    @GetMapping(value = "/jobs/{id}")
    public JobTitleViewModel getJobTitle(
	    @ApiParam(value = "The job title will be gotten from the database by his ID", required = true)
	    @PathVariable("id") Integer id) {
        return jobTitleService.getById(id);
    }

    @ApiOperation(value = "Create a job title in the database",
	    response = IdViewModel.class, code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, 
		    message = "The job title was not created in the database or Problems with server"),
	})
    @PostMapping("/jobs")
    public IdViewModel createJobTitle(
	    @ApiParam(value = "The job title data for creating in the database.", required = true)
	    @Valid @RequestBody JobTitlePayload newJobTitle) {	
	return jobTitleService.create(newJobTitle);
    }

    @ApiOperation(value = "Update an existing job title in the database.", code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, 
		    message = "The job title was not updated in the database or Problems with server")
	})
    @PutMapping(value = "/jobs/{id}")
    public void updateJobTitle(
	    @ApiParam(value = "The job title data for updating in the database.", required = true)
	    @Valid @RequestBody JobTitlePayload editedJobTitle) {
        jobTitleService.update(editedJobTitle);
    }

    @ApiOperation(value = "Delete an existing job title by Id from the database.", 
	    notes = "ID has to be greater than zero.", code = 200)
    @ApiResponses(value = {
	    @ApiResponse(code = 500, 
		    message = "The job title was not delated from the database or Problems with server")
	})
    @DeleteMapping(value = "/jobs/{id}")
    public void deleteJobTitle(
	    @ApiParam(value = "The job title will be deleted from the database by his ID.", required = true)
	    @PathVariable("id") Integer id) {
	jobTitleService.delete(id);
    }

}
