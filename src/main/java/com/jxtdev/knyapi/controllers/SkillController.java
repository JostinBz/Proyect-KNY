package com.jxtdev.knyapi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jxtdev.knyapi.dto.SkillDTO;
import com.jxtdev.knyapi.entities.Skill;
import com.jxtdev.knyapi.services.SkillService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/skills")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable Long id) {
        return new ResponseEntity<>(this.skillService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAll() {
        return new ResponseEntity<>(this.skillService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Skill> createCharacter(@RequestBody @Valid SkillDTO skillDTO) {
        // Guardar el personaje en la base de datos
        return new ResponseEntity<>(this.skillService.addSkill(skillDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillDTO> updateSkill(@PathVariable Long id, @RequestBody SkillDTO skillDTO) {
        SkillDTO skill = this.skillService.updateSkill(id, skillDTO);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable Long id) {
        return new ResponseEntity<>(this.skillService.deleteSkill(id), HttpStatus.NO_CONTENT);
    }
}
