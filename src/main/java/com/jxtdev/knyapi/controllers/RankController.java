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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ranks")
public class RankController {
    @Autowired
    private RankService rankService;

    @GetMapping("/{id}")
    public ResponseEntity<RankDTO> getRankById(@PathVariable Long id) {
        RankDTO rank = rankService.findById(id);
        return new ResponseEntity<>(rank, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RankDTO>> getAll() {
        List<RankDTO> ranks = rankService.findAll();
        return new ResponseEntity<>(ranks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RankDTO> createRank(@RequestBody @Valid RankDTO rankDTO) {
        RankDTO newRank = rankService.addRank(rankDTO);
        return new ResponseEntity<>(newRank, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RankDTO> updateRank(@PathVariable Long id, @RequestBody RankDTO rankDTO) {
        RankDTO updatedRank = rankService.updateRank(id, rankDTO);
        return new ResponseEntity<>(updatedRank, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRank(@PathVariable Long id) {
        rankService.deleteRankById(id);
        return new ResponseEntity<>("Rank successfully removed", HttpStatus.NO_CONTENT);
    }
}
