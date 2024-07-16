package com.jxtdev.knyapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxtdev.knyapi.entities.InspiredCharacter;
import com.jxtdev.knyapi.repositories.InspiredCharacterRepository;

@Service
public class InspiredCharacterService {
    @Autowired
    private InspiredCharacterRepository inspiredCharacterRepository;

    public List<InspiredCharacter> getAllCharacters(){
        return inspiredCharacterRepository.findAll();
    }

    public Optional<InspiredCharacter> getCharacter(Long id){
        return inspiredCharacterRepository.findById(id);
    }

    public InspiredCharacter addCharacter(InspiredCharacter character){
        return inspiredCharacterRepository.save(character);
    }

    public List<InspiredCharacter> addListCharacters(List<InspiredCharacter> characters){
        return inspiredCharacterRepository.saveAll(characters);
    }

    // Método para actualizar un personaje
    public InspiredCharacter updateCharacter(Long id, InspiredCharacter updatedCharacter) {
        return inspiredCharacterRepository.findById(id).map(character -> {
            character.setName(updatedCharacter.getName());
            character.setDescription(updatedCharacter.getDescription());
            character.setType(updatedCharacter.getType());
            character.setRank(updatedCharacter.getRank());
            character.setImageUrl(updatedCharacter.getImageUrl());
            character.setPowers(updatedCharacter.getPowers());
            return inspiredCharacterRepository.save(character);
        }).orElseThrow(() -> new RuntimeException("Character not found with id " + id));
    }

    public void deleteCharacterById(Long id){
        inspiredCharacterRepository.deleteById(id);
    }

    public void deleteCharacter(InspiredCharacter character){
        inspiredCharacterRepository.delete(character);;
    }
}
