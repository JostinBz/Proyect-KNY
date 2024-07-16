package com.jxtdev.knyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jxtdev.knyapi.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long>{
    
}
