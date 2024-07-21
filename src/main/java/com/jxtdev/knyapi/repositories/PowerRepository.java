package com.jxtdev.knyapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jxtdev.knyapi.entities.*;

public interface PowerRepository extends JpaRepository<Power, Long>{
    Optional<Power> findByName(String name);
}
