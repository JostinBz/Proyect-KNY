package com.jxtdev.knyapi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.jxtdev.knyapi.dto.TypeCharacterDTO;
import com.jxtdev.knyapi.entities.TypeCharacter;
import com.jxtdev.knyapi.services.TypeCharacterService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/typeCharacter")
public class TypeCharacterController {
    @Autowired
    private TypeCharacterService typeCharacterService;


    @PostMapping
    public ResponseEntity<String> createTypeCharacter(@RequestBody TypeCharacterDTO typeCharacterDTO) {
        try {
            TypeCharacter typeCharacter = typeCharacterService.findByName(typeCharacterDTO.getName());

            if (typeCharacter == null) {
                // Si no existe, crear uno nuevo
                typeCharacter = TypeCharacter.builder()
                        .name(typeCharacterDTO.getName())
                        .description(typeCharacterDTO.getDescripcion())
                        .build();

                typeCharacterService.addCharacter(typeCharacter);
                return ResponseEntity.status(HttpStatus.CREATED).body("TypeCharacter created successfully");
            } else {
                // Si ya existe, retornar un mensaje indicando que ya existe
                return ResponseEntity.status(HttpStatus.OK).body("TypeCharacter already exists");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create TypeCharacter: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<TypeCharacter>> getAllTypeCharacters() {
        try {
            List<TypeCharacter> typeCharacters = typeCharacterService.findAll();
            return ResponseEntity.ok().body(typeCharacters);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeCharacter> getTypeCharacterById(@PathVariable Long id) {
        try {
            TypeCharacter typeCharacter = typeCharacterService.findById(id);
            return ResponseEntity.ok().body(typeCharacter);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
