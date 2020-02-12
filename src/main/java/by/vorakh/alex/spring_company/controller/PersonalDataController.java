package by.vorakh.alex.spring_company.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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

import by.vorakh.alex.spring_company.model.payload.PersonalDataPayload;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.model.view_model.PersonalDataViewModel;
import by.vorakh.alex.spring_company.service.PersonalDataService;

@Api(description="Operations pertaining to personal data")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 1800)
@RequestMapping("/project")
public class PersonalDataController {
    @Autowired
    private PersonalDataService personalDataService;
    
    @ApiOperation(value = "Get a list of existing personal data " 
            + "from the database", response = PersonalDataViewModel.class,
            responseContainer = "List")
    @ApiResponses({
        @ApiResponse(code = 500, message = "Problems with server")
    })
    @GetMapping("/people")
    public List<PersonalDataViewModel> getPersonalDatas() {
        return personalDataService.getAll();
    }

    @ApiOperation(value = "Get a personal data by Id from the database", 
      	    notes = "ID has to be greater than zero.", 
      	    response = PersonalDataViewModel.class)
    @ApiResponses({
        @ApiResponse(code = 500, message = "The personal data does not exist " 
              + " or Problems with server")
    })
    @GetMapping("/people/{id}")
    public PersonalDataViewModel getPersonalData(
      	    @ApiParam(value = "The personal data will be gotten " 
                    + "from the database by his ID", required = true)
      	    @PathVariable("id") @Positive @NotNull Integer id) {
        return personalDataService.getById(id);
    }

    @ApiOperation(value = "Create a personal data in the database",
            response = IdViewModel.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request: wrong data"),
        @ApiResponse(code = 500, message = "The personal data " 
                + " was not created in the database or Problems with server")
    })
    @PostMapping("/people")
    public IdViewModel createPersonalData(
      	    @ApiParam(value = "The personal data data for creating " 
                    + "in the database.", required = true)
      	    @Valid @RequestBody PersonalDataPayload newPersonalData) {
        return personalDataService.create(newPersonalData);
    }

    @ApiOperation("Update an existing personal data in the database.")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request: wrong data"),
            @ApiResponse(code = 500, message = "The personal data " 
                    + "was not updated in the database or Problems with server")
    })
    @PutMapping("/people/")
    public void updatePersonalData(
            @ApiParam(value = "The personal data data for updating " 
                    + "in the database.", required = true)
            @Valid @RequestBody PersonalDataPayload editedPersonalData) {
        personalDataService.update(editedPersonalData);
    }

    @ApiOperation(value = "Delete an existing personal data  by Id " 
            + "from the database.", notes = "ID has to be greater than zero.")
    @ApiResponses({
        @ApiResponse(code = 500, message = "The personal data was not delated" 
                + " from the database or Problems with server")
    })
    @DeleteMapping("/people/{id}")
    public void deletePersonalData(
	    @ApiParam(value = "The personal data will be deleted from the database " 
              + "by his ID.", required = true)
	    @PathVariable("id") @Positive @NotNull Integer id) {
        personalDataService.delete(id);
    }
}
