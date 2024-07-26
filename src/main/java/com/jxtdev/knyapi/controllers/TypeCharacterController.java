package com.jxtdev.knyapi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jxtdev.knyapi.dto.TypeCharacterDTO;
import com.jxtdev.knyapi.services.TypeCharacterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/typeCharacter")
@Tag(name = "Type Characters")
public class TypeCharacterController {
    @Autowired
    private TypeCharacterService typeCharacterService;

    @Operation(summary = "Create a new character type", description = "Add a new character type to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Character type created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TypeCharacterDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<TypeCharacterDTO> createTypeCharacter(@RequestBody @Valid TypeCharacterDTO typeCharacterDTO) {
        TypeCharacterDTO newTypeCharacter = typeCharacterService.addCharacter(typeCharacterDTO);
        return new ResponseEntity<>(newTypeCharacter, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all character types", description = "Retrieve a list of all character types.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of character types retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TypeCharacterDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<TypeCharacterDTO>> getAllTypeCharacters() {
        List<TypeCharacterDTO> typeCharacters = typeCharacterService.findAll();
        return new ResponseEntity<>(typeCharacters, HttpStatus.OK);
    }

    @Operation(summary = "Get a character type by ID", description = "Retrieve a character type by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character type retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TypeCharacterDTO.class))),
            @ApiResponse(responseCode = "404", description = "Character type not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TypeCharacterDTO> getTypeCharacterById(@PathVariable Long id) {
        try {
            TypeCharacterDTO typeCharacter = typeCharacterService.findById(id);
            return new ResponseEntity<>(typeCharacter, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update a character type", description = "Update an existing character type's information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character type updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TypeCharacterDTO.class))),
            @ApiResponse(responseCode = "404", description = "Character type not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TypeCharacterDTO> updateTypeCharacter(@PathVariable Long id,
            @RequestBody TypeCharacterDTO typeCharacterDTO) {
        TypeCharacterDTO updatedTypeCharacter = typeCharacterService.updateTypeCharacter(id, typeCharacterDTO);
        return new ResponseEntity<>(updatedTypeCharacter, HttpStatus.OK);
    }

    @Operation(summary = "Delete a character type", description = "Delete a character type by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Character type deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Character type not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTypeCharacter(@PathVariable Long id) {
        typeCharacterService.deleteSkill(id);
        return new ResponseEntity<>("Type character successfully removed", HttpStatus.NO_CONTENT);
    }
}
