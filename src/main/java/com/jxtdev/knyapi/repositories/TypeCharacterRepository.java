package com.jxtdev.knyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jxtdev.knyapi.entities.TypeCharacter;

@Repository
public interface TypeCharacterRepository extends JpaRepository<TypeCharacter,Long>{
    TypeCharacter findByName(String name);
}
