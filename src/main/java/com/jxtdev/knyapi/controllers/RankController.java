package com.jxtdev.knyapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jxtdev.knyapi.dto.RankDTO;
import com.jxtdev.knyapi.services.RankService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ranks")
@Tag(name = "Ranks Characters", description = "Operations related to ranks in the Kimetsu no Yaiba universe")
public class RankController {
    @Autowired
    private RankService rankService;

    @Operation(summary = "Get a rank by ID", description = "Retrieve a rank by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rank retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RankDTO.class))),
            @ApiResponse(responseCode = "404", description = "Rank not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RankDTO> getRankById(@PathVariable Long id) {
        RankDTO rank = rankService.findById(id);
        return new ResponseEntity<>(rank, HttpStatus.OK);
    }

    @Operation(summary = "Get all ranks", description = "Retrieve a list of all ranks.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of ranks retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RankDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<RankDTO>> getAll() {
        List<RankDTO> ranks = rankService.findAll();
        return new ResponseEntity<>(ranks, HttpStatus.OK);
    }

    @Operation(summary = "Create a new rank", description = "Add a new rank to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rank created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RankDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<RankDTO> createRank(@RequestBody @Valid RankDTO rankDTO) {
        RankDTO newRank = rankService.addRank(rankDTO);
        return new ResponseEntity<>(newRank, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a rank", description = "Update an existing rank's information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rank updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RankDTO.class))),
            @ApiResponse(responseCode = "404", description = "Rank not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RankDTO> updateRank(@PathVariable Long id, @RequestBody RankDTO rankDTO) {
        RankDTO updatedRank = rankService.updateRank(id, rankDTO);
        return new ResponseEntity<>(updatedRank, HttpStatus.OK);
    }

    @Operation(summary = "Delete a rank", description = "Delete a rank by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Rank deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Rank not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRank(@PathVariable Long id) {
        rankService.deleteRankById(id);
        return new ResponseEntity<>("Rank successfully removed", HttpStatus.NO_CONTENT);
    }
}
