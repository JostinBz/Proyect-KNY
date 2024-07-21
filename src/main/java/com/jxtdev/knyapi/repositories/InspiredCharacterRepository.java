package com.jxtdev.knyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jxtdev.knyapi.entities.*;

public interface InspiredCharacterRepository extends JpaRepository<InspiredCharacter, Long>{
    InspiredCharacter findByName(String name);
}
