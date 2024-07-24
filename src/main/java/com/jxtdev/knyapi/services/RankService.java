package com.jxtdev.knyapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxtdev.knyapi.advice.ResourceNotFoundException;
import com.jxtdev.knyapi.dto.RankDTO;
import com.jxtdev.knyapi.entities.Rank;
import com.jxtdev.knyapi.repositories.RankRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RankService {
    @Autowired
    private RankRepository rankRepository;

    public RankDTO findById(Long id) {
        Rank rank = rankRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rank with id " + id + " not found"));

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(rank, RankDTO.class);
    }

    public List<RankDTO> findAll() {
        List<Rank> ranks = rankRepository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        return ranks.stream()
                .map(rank -> modelMapper.map(rank, RankDTO.class))
                .collect(Collectors.toList());
    }

    public RankDTO findByName(String name) {
        Optional<Rank> rank = this.rankRepository.findByName(name);
        if (rank.isPresent()) {
            throw new EntityNotFoundException("Rank not found with name: " + name);
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(rank, RankDTO.class);
    }

    public RankDTO addRank(RankDTO rankDTO) {
        if (rankRepository.findByName(rankDTO.getName()).isPresent()) {
            throw new IllegalArgumentException("Rank with the name " + rankDTO.getName() + " already exists.");
        }

        Rank rank = Rank.builder()
                .name(rankDTO.getName())
                .description(rankDTO.getDescription())
                .build();
        Rank savedRank = rankRepository.save(rank);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(savedRank, RankDTO.class);
    }

    public RankDTO updateRank(Long id, RankDTO rankDTO) {
        Rank existingRank = rankRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rank with ID " + id + " not found."));

        existingRank.setName(rankDTO.getName());
        existingRank.setDescription(rankDTO.getDescription());
        // Actualiza otros campos según sea necesario

        Rank updatedRank = rankRepository.save(existingRank);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(updatedRank, RankDTO.class);
    }

    public void deleteRankById(Long id) {
        Rank rank = rankRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rank with ID " + id + " not found."));
        rankRepository.delete(rank);
    }
}
