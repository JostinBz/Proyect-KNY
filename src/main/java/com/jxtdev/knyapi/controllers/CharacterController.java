package com.jxtdev.knyapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.jxtdev.knyapi.dto.CharacterDTO;
import com.jxtdev.knyapi.dto.PowerDTO;
import com.jxtdev.knyapi.dto.RankDTO;
import com.jxtdev.knyapi.dto.TypeCharacterDTO;
import com.jxtdev.knyapi.entities.Character;
import com.jxtdev.knyapi.entities.Power;
import com.jxtdev.knyapi.entities.Rank;
import com.jxtdev.knyapi.entities.TypeCharacter;
import com.jxtdev.knyapi.services.CharacterService;
import com.jxtdev.knyapi.services.PowerService;
import com.jxtdev.knyapi.services.RankService;
import com.jxtdev.knyapi.services.TypeCharacterService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService characterService;
    private final TypeCharacterService typeCharacterService;
    private final RankService rankService;
    private final PowerService powerService;

    public CharacterController(CharacterService characterService,
                               TypeCharacterService typeCharacterService,
                               RankService rankService,
                               PowerService powerService) {
        this.characterService = characterService;
        this.typeCharacterService = typeCharacterService;
        this.rankService = rankService;
        this.powerService = powerService;
    }

    @GetMapping
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> characters = characterService.getAllCharacters();
        return ResponseEntity.ok(characters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        Character character = characterService.getCharacter(id);
        return ResponseEntity.ok(character);
    }

    @PostMapping
    public ResponseEntity<String> createCharacter(@RequestBody CharacterDTO characterDTO) {
        try {
            // Obtener y asignar TypeCharacter
            TypeCharacterDTO typeCharacterDTO = characterDTO.getTypeCharacter();
            TypeCharacter typeCharacter = typeCharacterService.findById(typeCharacterDTO.getId());

            // Obtener y asignar Rank
            RankDTO rankDTO = characterDTO.getRankCharacter();
            Rank rank = rankService.findById(rankDTO.getId());

            // Obtener y asignar Powers
            List<PowerDTO> powerDTOs = characterDTO.getPowers();
            List<Power> powers = powerService.findAllByIds(powerDTOs.stream()
            .map(PowerDTO::getId).toList());

            // Convertir DTO a entidad Character
            Character character = Character.builder()
            .name(characterDTO.getName())
            .description(characterDTO.getDescription())
            .imageUrl(characterDTO.getImageUrl())
            .powers(powers)
            .rank(rank)
            .type(typeCharacter)
            .build();

            // Guardar el personaje en la base de datos
            characterService.addCharacter(character);

            return ResponseEntity.status(HttpStatus.CREATED).body("Character created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create character: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id, @RequestBody Character characterDetails) {
        Character updatedCharacter = characterService.updateCharacter(id, characterDetails);
        return ResponseEntity.ok(updatedCharacter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterService.deleteCharacterById(id);
        return ResponseEntity.noContent().build();
    }
}
