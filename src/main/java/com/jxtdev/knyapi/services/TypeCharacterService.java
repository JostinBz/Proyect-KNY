package com.jxtdev.knyapi.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxtdev.knyapi.advice.ResourceNotFoundException;
import com.jxtdev.knyapi.dto.TypeCharacterDTO;
import com.jxtdev.knyapi.entities.TypeCharacter;
import com.jxtdev.knyapi.repositories.TypeCharacterRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TypeCharacterService {
    @Autowired
    private TypeCharacterRepository typeCharacterRepository;

    public TypeCharacterDTO addCharacter(TypeCharacterDTO typeCharacterDTO) {
        Optional<TypeCharacter> typeCharacter = this.typeCharacterRepository.findByName(typeCharacterDTO.getName());

        if (!typeCharacter.isPresent()) {
            TypeCharacter typeSave = this.typeCharacterRepository.save(typeCharacter.get());
            ModelMapper modelMapper = new ModelMapper();
            TypeCharacterDTO newType = modelMapper.map(typeSave, TypeCharacterDTO.class);
            return newType;
        } else
            throw new IllegalArgumentException(
                    "Character with the name " + typeCharacterDTO.getName() + " already exists.");
    }

    public TypeCharacterDTO updateTypeCharacter(Long id, TypeCharacterDTO typeCharacterDTO) {
        Optional<TypeCharacter> existingCharacter = this.typeCharacterRepository.findById(id);

        if (!existingCharacter.isPresent()) {
            throw new EntityNotFoundException("Type of character with ID " + id + " not found.");
        }

        TypeCharacter typeCharacter = existingCharacter.get();
        typeCharacter.setName(typeCharacterDTO.getName());
        // Actualiza otros campos según sea necesario
        // typeCharacter.setOtherField(typeCharacterDTO.getOtherField());

        TypeCharacter updatedCharacter = this.typeCharacterRepository.save(typeCharacter);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(updatedCharacter, TypeCharacterDTO.class);
    }

    public String deleteSkill(Long id) {
        TypeCharacter type = this.typeCharacterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
        this.typeCharacterRepository.delete(type);
        return "Type character successfully removed";
    }

    public TypeCharacterDTO findById(Long id) {
        TypeCharacter type = typeCharacterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TypeCharacter with id " + id + " not found"));

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(type, TypeCharacterDTO.class);
    }

    public List<TypeCharacterDTO> findAll() {
        List<TypeCharacter> types = typeCharacterRepository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        return types.stream().map(type -> modelMapper.map(types, TypeCharacterDTO.class))
                .toList();
    }

}
