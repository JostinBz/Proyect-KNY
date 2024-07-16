package com.jxtdev.knyapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxtdev.knyapi.repositories.CharacterRepository;
import com.jxtdev.knyapi.entities.Character;

@Service
public class CharacterService{
    @Autowired
    private CharacterRepository characterRepository;

    public List<Character> getAllCharacters(){
        return characterRepository.findAll();
    }

    public Character getCharacter(Long id){
        Optional<Character> character = characterRepository.findById(id);
        return character.orElse(null);
    }

    public Character addCharacter(Character character){
        return characterRepository.save(character);
    }

    public List<Character> addListCharacters(List<Character> characters){
        return characterRepository.saveAll(characters);
    }

    // Método para actualizar un personaje
    public Character updateCharacter(Long id, Character updatedCharacter) {
        return characterRepository.findById(id).map(character -> {
            character.setName(updatedCharacter.getName());
            character.setDescription(updatedCharacter.getDescription());
            character.setType(updatedCharacter.getType());
            character.setRank(updatedCharacter.getRank());
            character.setImageUrl(updatedCharacter.getImageUrl());
            character.setPowers(updatedCharacter.getPowers());
            return characterRepository.save(character);
        }).orElseThrow(() -> new RuntimeException("Character not found with id " + id));
    }

    public void deleteCharacterById(Long id){
        characterRepository.deleteById(id);
    }

    public void deleteCharacter(Character character){
        characterRepository.delete(character);;
    }
}
