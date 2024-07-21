package com.jxtdev.knyapi.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.jxtdev.knyapi.dto.CharacterDTO;
import com.jxtdev.knyapi.services.CharacterService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getAllCharacters() {
        List<CharacterDTO> characters = characterService.getAllCharacters();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable Long id) {
        CharacterDTO character = characterService.getCharacter(id);
        return new ResponseEntity<>(character, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> createCharacter(@RequestBody @Valid CharacterDTO characterDTO) {
        CharacterDTO newCharacter = characterService.addCharacter(characterDTO);
        return new ResponseEntity<>(newCharacter, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> updateCharacter(@PathVariable Long id, @RequestBody @Valid CharacterDTO characterDTO) {
        CharacterDTO updatedCharacter = characterService.updateCharacter(id, characterDTO);
        return new ResponseEntity<>(updatedCharacter, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCharacter(@PathVariable Long id) {
        characterService.deleteCharacterById(id);
        return new ResponseEntity<>("Character successfully removed", HttpStatus.NO_CONTENT);
    }
}
