package com.jxtdev.knyapi.controllers;

import com.jxtdev.knyapi.dto.CharacterDTO;
import com.jxtdev.knyapi.services.CharacterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/characters")
@Tag(name = "Characters", description = "Operations related to characters in the Kimetsu no Yaiba universe")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @GetMapping
    @Operation(description = "Get all characters that are registered within the database consumed by the api", responses = {
            @ApiResponse(responseCode = "400", ref = "badRequest"),
            @ApiResponse(responseCode = "500", ref = "internalServerError"),
            @ApiResponse(responseCode = "200", ref = "successfulResponse")
    })
    public ResponseEntity<List<CharacterDTO>> getAllCharacters() {
        List<CharacterDTO> characters = characterService.getAllCharacters();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a character by ID", description = "Retrieve a character by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDTO.class))),
            @ApiResponse(responseCode = "404", description = "Character not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable Long id) {
        CharacterDTO character = characterService.getCharacter(id);
        return new ResponseEntity<>(character, HttpStatus.OK);
    }

    @Operation(summary = "Create a new character", description = "Add a new character to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Character created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<CharacterDTO> createCharacter(@RequestBody @Valid CharacterDTO characterDTO) {
        CharacterDTO newCharacter = characterService.addCharacter(characterDTO);
        return new ResponseEntity<>(newCharacter, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a character", description = "Update an existing character's information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDTO.class))),
            @ApiResponse(responseCode = "404", description = "Character not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> updateCharacter(@PathVariable Long id,
            @RequestBody @Valid CharacterDTO characterDTO) {
        CharacterDTO updatedCharacter = characterService.updateCharacter(id, characterDTO);
        return new ResponseEntity<>(updatedCharacter, HttpStatus.OK);
    }

    @Operation(summary = "Delete a character", description = "Delete a character by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Character deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Character not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCharacter(@PathVariable Long id) {
        characterService.deleteCharacterById(id);
        return new ResponseEntity<>("Character successfully removed", HttpStatus.NO_CONTENT);
    }
}
