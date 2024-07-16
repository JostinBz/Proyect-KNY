package com.jxtdev.knyapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxtdev.knyapi.entities.Rank;
import com.jxtdev.knyapi.repositories.RankRepository;

@Service
public class RankService {
    @Autowired
    private RankRepository rankRepository;

    
    public Rank findById(Long id) {
        return rankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rank with id " + id + " not found"));
    }

    public List<Rank> findAll() {
        return rankRepository.findAll();
    }
}
