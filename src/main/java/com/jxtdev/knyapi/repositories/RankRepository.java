package com.jxtdev.knyapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jxtdev.knyapi.entities.*;

@Repository
public interface RankRepository extends JpaRepository<Rank,Long>{
    Optional<Rank> findByName(String name);
}
