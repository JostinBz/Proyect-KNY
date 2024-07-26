package com.jxtdev.knyapi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jxtdev.knyapi.dto.PowerDTO;
import com.jxtdev.knyapi.services.PowerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/powers")
@Tag(name = "Power", description = "Operations related to powers in the Kimetsu no Yaiba universe")
public class PowerController {

    @Autowired
    private PowerService powerService;

    @Operation(summary = "Get a power by ID", description = "Retrieve a power by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Power retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PowerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Power not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PowerDTO> getPowerById(@PathVariable Long id) {
        return new ResponseEntity<>(this.powerService.findPowerById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get all powers", description = "Retrieve a list of all powers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of powers retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PowerDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<PowerDTO>> getAllPowers() {
        List<PowerDTO> powers = powerService.findAllPowers();
        return new ResponseEntity<>(powers, HttpStatus.OK);
    }

    @Operation(summary = "Create a new power", description = "Add a new power to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Power created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PowerDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<PowerDTO> createCharacter(@RequestBody @Valid PowerDTO powerDTO) {
        PowerDTO powerSave = this.powerService.addPower(powerDTO);
        return new ResponseEntity<>(powerSave, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a power", description = "Update an existing power's information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Power updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PowerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Power not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PowerDTO> updatePower(@PathVariable Long id, @RequestBody PowerDTO powerDTO) {
        return new ResponseEntity<>(this.powerService.updatePower(id, powerDTO), HttpStatus.OK);
    }

    @Operation(summary = "Delete a power", description = "Delete a power by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Power deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Power not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePower(@PathVariable Long id) {
        return new ResponseEntity<>(this.powerService.deletePower(id), HttpStatus.NO_CONTENT);
    }

}
