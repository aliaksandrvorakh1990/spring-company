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

import by.vorakh.alex.spring_company.model.payload.SkillPayload;
import by.vorakh.alex.spring_company.model.view_model.IdViewModel;
import by.vorakh.alex.spring_company.model.view_model.SkillViewModel;
import by.vorakh.alex.spring_company.service.SkillService;

@Api(description="Operations pertaining to skill")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 1800)
@RequestMapping("/project")
public class SkillController {
    @Autowired
    private SkillService skillService;
    
    @ApiOperation(value = "Get a list of existing skils from the database",
            response = SkillViewModel.class, responseContainer = "List")
    @ApiResponses({
        @ApiResponse(code = 500, message = "Problems with server")
    })
    @GetMapping("/skills")
    public List<SkillViewModel> getSkills() {
        return skillService.getAll();
    }

    @ApiOperation(value = "Get a skill by Id from the database", 
	    notes = "ID has to be greater than zero.", 
	    response = SkillViewModel.class)
    @ApiResponses({
        @ApiResponse(code = 500, message = "The skill does not exist or " 
                + "Problems with server")
    })
    @GetMapping("/skills/{id}")
    public SkillViewModel getSkill(
            @ApiParam(value = "The skill will be gotten from the database" 
                    + " by his ID", required = true)
            @PathVariable("id")@Valid @Positive @NotNull Integer id) {
        return skillService.getById(id);
    }

    @ApiOperation(value = "Create an skill in the database",
            response = IdViewModel.class)
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request: wrong data"),
        @ApiResponse(code = 500,message = "The skill was not created " 
                + "in the database or Problems with server"),
    })
    @PostMapping("/skills")
    public IdViewModel createSkill(
            @ApiParam(value = "The skill data for creating in the database.", 
                    required = true)
            @Valid @RequestBody SkillPayload newSkill) {
        return skillService.create(newSkill);
    }

    @ApiOperation("Update an existing skill in the database.")
    @ApiResponses({
        @ApiResponse(code = 400, message = "Bad Request: wrong data"),
        @ApiResponse(code = 500, message = "The skill was not updated " 
                + "in the database or Problems with server")
    })
    @PutMapping("/skills/")
    public void updateSkill(
            @ApiParam(value = "The skill data for updating in the database.", 
                    required = true)
            @Valid @RequestBody SkillPayload editedSkill) {
        skillService.update(editedSkill);
    }

    @ApiOperation(value = "Delete an existing skill by Id from the database.", 
            notes = "ID has to be greater than zero.")
    @ApiResponses({
        @ApiResponse(code = 500, message = "The skill was not delated " 
                + "from the database or Problems with server")
    })
    @DeleteMapping("/skills/{id}")
    public void deleteSkill(
            @ApiParam(value = "The skill will be deleted from the database " 
                    + "by his ID.", required = true)
            @PathVariable("id") @Valid @Positive @NotNull Integer id) {
        skillService.delete(id);
    }
}
