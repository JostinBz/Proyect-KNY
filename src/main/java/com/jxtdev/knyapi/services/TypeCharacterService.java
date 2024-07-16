package com.jxtdev.knyapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxtdev.knyapi.entities.TypeCharacter;
import com.jxtdev.knyapi.repositories.TypeCharacterRepository;

@Service
public class TypeCharacterService {
    @Autowired
    private TypeCharacterRepository typeCharacterRepository;


    public TypeCharacter addCharacter(TypeCharacter typeCharacter){
        return typeCharacterRepository.save(typeCharacter);
    }

    public List<TypeCharacter> addListCharacters(List<TypeCharacter> typeCharacters){
        return typeCharacterRepository.saveAll(typeCharacters);
    }

    public TypeCharacter findById(Long id) {
        return typeCharacterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TypeCharacter with id " + id + " not found"));
    }

    public List<TypeCharacter> findAll() {
        return typeCharacterRepository.findAll();
    }

    public TypeCharacter findByName(String name) {
        return typeCharacterRepository.findByName(name);
    }
}
