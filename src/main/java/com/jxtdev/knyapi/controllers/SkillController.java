package com.jxtdev.knyapi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jxtdev.knyapi.dto.SkillDTO;
import com.jxtdev.knyapi.services.SkillService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/skills")
@Tag(name = "Skill", description = "Operations related to skills in the Kimetsu no Yaiba universe")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @Operation(summary = "Get a skill by ID", description = "Retrieve a skill by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Skill retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SkillDTO.class))),
            @ApiResponse(responseCode = "404", description = "Skill not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable Long id) {
        return new ResponseEntity<>(this.skillService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get all skills", description = "Retrieve a list of all skills.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of skills retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SkillDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAll() {
        return new ResponseEntity<>(this.skillService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Create a new skill", description = "Add a new skill to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Skill created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SkillDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<SkillDTO> createCharacter(@RequestBody @Valid SkillDTO skillDTO) {
        // Guardar el personaje en la base de datos
        return new ResponseEntity<>(this.skillService.addSkill(skillDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a skill", description = "Update an existing skill's information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Skill updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SkillDTO.class))),
            @ApiResponse(responseCode = "404", description = "Skill not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SkillDTO> updateSkill(@PathVariable Long id, @RequestBody SkillDTO skillDTO) {
        SkillDTO skill = this.skillService.updateSkill(id, skillDTO);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @Operation(summary = "Delete a skill", description = "Delete a skill by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Skill deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Skill not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable Long id) {
        return new ResponseEntity<>(this.skillService.deleteSkill(id), HttpStatus.NO_CONTENT);
    }
}
