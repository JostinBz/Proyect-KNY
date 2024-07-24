package com.jxtdev.knyapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jxtdev.knyapi.advice.ResourceNotFoundException;
import com.jxtdev.knyapi.dto.*;
import com.jxtdev.knyapi.entities.*;
import com.jxtdev.knyapi.repositories.SkillRepository;

@Service
public class SkillService {
    @Autowired
    SkillRepository skillRepository;

    public SkillDTO findById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rank with id " + id + " not found"));

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(skill, SkillDTO.class);
    }

    public List<SkillDTO> findAll() {
        List<Skill> listSkill = skillRepository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        return listSkill.stream()
                .map(skill -> modelMapper.map(skill, SkillDTO.class))
                .collect(Collectors.toList());
    }

    public SkillDTO addSkill(SkillDTO skillDTO) {

        if (this.skillRepository.findByName(skillDTO.getName()).isPresent()) {
            throw new IllegalArgumentException("This skill is already in use");
        }
        Skill skill = Skill.builder()
                .name(skillDTO.getName())
                .description(skillDTO.getDescription())
                .build();
        Skill skillSave =  this.skillRepository.save(skill);
        ModelMapper modelMapper = new ModelMapper();
        SkillDTO newSkillDTO = modelMapper.map(skillSave, SkillDTO.class);
        return newSkillDTO;
    }

    public SkillDTO updateSkill(Long id, SkillDTO skillDTO) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rank with id " + id + " not found"));

        skill.setName(skillDTO.getName());
        skill.setDescription(skillDTO.getDescription());
        Skill skillUpdate = this.skillRepository.save(skill);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(skillUpdate, SkillDTO.class);
    }

    public String deleteSkill(Long id) {
        Skill skill = this.skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
        this.skillRepository.delete(skill);
        return "Skill successfully removed";
    }

}
