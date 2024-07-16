package com.jxtdev.knyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jxtdev.knyapi.entities.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
}
