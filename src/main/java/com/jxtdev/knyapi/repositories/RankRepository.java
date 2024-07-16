package com.jxtdev.knyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jxtdev.knyapi.entities.Rank;

@Repository
public interface RankRepository extends JpaRepository<Rank,Long>{
    
}
