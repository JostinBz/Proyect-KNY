package com.jxtdev.knyapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jxtdev.knyapi.advice.ResourceNotFoundException;
import com.jxtdev.knyapi.dto.PowerDTO;
import com.jxtdev.knyapi.entities.Power;
import com.jxtdev.knyapi.entities.Skill;
import com.jxtdev.knyapi.repositories.PowerRepository;
import com.jxtdev.knyapi.repositories.SkillRepository;

@Service
public class PowerService {

    @Autowired
    private PowerRepository powerRepository;

    @Autowired
    private SkillRepository skillRepository;

    public List<PowerDTO> findAllPowers() {
        List<Power> listPowers = powerRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        return listPowers.stream()
                .map(powerDTO -> modelMapper.map(powerDTO, PowerDTO.class))
                .collect(Collectors.toList());
    }

    public PowerDTO findPowerById(Long id) {
        Power power = powerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Power not found"));

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(power, PowerDTO.class);
    }

    public PowerDTO addPower(PowerDTO powerDTO) {
        if (this.powerRepository.findByName(powerDTO.getName()).isPresent()) {
            throw new IllegalArgumentException("Power with name " + powerDTO.getName() + " already exists");
        }

        Skill skill;
        Optional<Skill> sOptional = skillRepository.findByName(powerDTO.getSkill().getName());
        if (sOptional.isPresent()) {
            skill = Skill.builder()
            .name(powerDTO.getSkill().getName())
            .description(powerDTO.getSkill().getDescription())
            .build();
        }else throw new IllegalArgumentException("Power with name " + powerDTO.getSkill().getName() + " already exists");

        Power power = Power.builder()
                .name(powerDTO.getName())
                .description(powerDTO.getDescription())
                .skill(skill)
                .build();
        Power powerSave = this.powerRepository.save(power);
        ModelMapper MMpPowerDTO = new ModelMapper();
        return MMpPowerDTO.map(powerSave, PowerDTO.class);
    }

    public List<Power> addListPower(List<PowerDTO> powersDTO) {
        List<Power> powers = new ArrayList<>();

        for (PowerDTO powerDTO : powersDTO) {
            if (powerDTO != null) {
                // Validar si el poder ya existe en la base de datos por su nombre
                Optional<Power> existingPower = this.powerRepository.findByName(powerDTO.getName());
                if (existingPower.isPresent()) {
                    throw new IllegalArgumentException("Skill with name " + powerDTO.getName() + " not found");
                } else {
                    // Si no existe, construir y añadir el nuevo poder a la lista
                    Power power = Power.builder()
                            .name(powerDTO.getName())
                            .description(powerDTO.getDescription())
                            .build();
                    powers.add(power);
                }
            }
        }

        // Guardar la lista de poderes en la base de datos
        return powerRepository.saveAll(powers);
    }

    // Método para actualizar un personaje
    public PowerDTO updatePower(Long id, PowerDTO powerDTO) {
        Power power = this.powerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Power not found"));

        power.setName(powerDTO.getName());
        power.setDescription(powerDTO.getDescription());
        ModelMapper modelMapper = new ModelMapper();
        Skill skillPower = modelMapper.map(powerDTO.getSkill(), Skill.class);
        power.setSkill(skillPower);
        Power updatedPower = powerRepository.save(power);
        ModelMapper modelMapperPower = new ModelMapper();
        return modelMapperPower.map(updatedPower, PowerDTO.class);
    }

    public String deletePower(Long id) {
        Power power = this.powerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Power not found"));

        this.powerRepository.delete(power);
        return "power successfully removed";
    }

}
