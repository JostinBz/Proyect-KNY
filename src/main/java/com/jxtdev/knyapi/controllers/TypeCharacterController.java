package com.jxtdev.knyapi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.jxtdev.knyapi.dto.TypeCharacterDTO;
import com.jxtdev.knyapi.services.TypeCharacterService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/typeCharacter")
public class TypeCharacterController {
    @Autowired
    private TypeCharacterService typeCharacterService;

    @PostMapping
    public ResponseEntity<TypeCharacterDTO> createTypeCharacter(@RequestBody @Valid TypeCharacterDTO typeCharacterDTO) {
        TypeCharacterDTO newTypeCharacter = typeCharacterService.addCharacter(typeCharacterDTO);
        return new ResponseEntity<>(newTypeCharacter, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TypeCharacterDTO>> getAllTypeCharacters() {
        List<TypeCharacterDTO> typeCharacters = typeCharacterService.findAll();
        return new ResponseEntity<>(typeCharacters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeCharacterDTO> getTypeCharacterById(@PathVariable Long id) {
        try {
            TypeCharacterDTO typeCharacter = typeCharacterService.findById(id);
            return new ResponseEntity<>(typeCharacter, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeCharacterDTO> updateTypeCharacter(@PathVariable Long id, @RequestBody TypeCharacterDTO typeCharacterDTO) {
        TypeCharacterDTO updatedTypeCharacter = typeCharacterService.updateTypeCharacter(id, typeCharacterDTO);
        return new ResponseEntity<>(updatedTypeCharacter, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTypeCharacter(@PathVariable Long id) {
        typeCharacterService.deleteSkill(id);
        return new ResponseEntity<>("Type character successfully removed", HttpStatus.NO_CONTENT);
    }
}

