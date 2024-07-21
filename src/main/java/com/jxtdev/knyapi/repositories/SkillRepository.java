package com.jxtdev.knyapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jxtdev.knyapi.entities.*;

public interface SkillRepository extends JpaRepository<Skill, Long>{
    Optional<Skill> findByName(String name);
}
