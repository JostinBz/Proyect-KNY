package com.jxtdev.knyapi.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jxtdev.knyapi.dto.CharacterDTO;
import com.jxtdev.knyapi.dto.PowerDTO;
import com.jxtdev.knyapi.dto.SkillDTO;
import com.jxtdev.knyapi.entities.Power;
import com.jxtdev.knyapi.entities.Skill;
import com.jxtdev.knyapi.services.PowerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/powers")
public class PowerController {

    @Autowired
    private PowerService powerService;

    @GetMapping("/{id}")
    public ResponseEntity<PowerDTO> getPowerById(@PathVariable Long id) {
        return new ResponseEntity<>(this.powerService.findPowerById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PowerDTO>> getAllPowers() {
        List<PowerDTO> powers = powerService.findAllPowers();
        return new ResponseEntity<>(powers,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PowerDTO> createCharacter(@RequestBody @Valid PowerDTO powerDTO) {
        PowerDTO powerSave = this.powerService.addPower(powerDTO);
        return new ResponseEntity<>(powerSave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PowerDTO> updatePower(@PathVariable Long id, @RequestBody PowerDTO powerDTO) {
        return new ResponseEntity<>(this.powerService.updatePower(id, powerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePower(@PathVariable Long id) {
        return new ResponseEntity<>(this.powerService.deletePower(id), HttpStatus.NO_CONTENT);
    }

}
