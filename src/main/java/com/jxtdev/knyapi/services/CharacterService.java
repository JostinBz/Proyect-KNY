package com.jxtdev.knyapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jxtdev.knyapi.repositories.*;
import com.jxtdev.knyapi.advice.ResourceNotFoundException;
import com.jxtdev.knyapi.dto.CharacterDTO;
import com.jxtdev.knyapi.dto.PowerDTO;
import com.jxtdev.knyapi.entities.Character;
import com.jxtdev.knyapi.entities.Power;
import com.jxtdev.knyapi.entities.Rank;
import com.jxtdev.knyapi.entities.TypeCharacter;

@Service
public class CharacterService {
        @Autowired
        private CharacterRepository characterRepository;

        @Autowired
        private TypeCharacterRepository typeCharacterRepository;

        @Autowired
        private RankRepository rankRepository;

        @Autowired
        private PowerRepository powerRepository;

        public List<CharacterDTO> getAllCharacters() {
                ModelMapper modelMapper = new ModelMapper();
                List<Character> characters = characterRepository.findAll();
                return characters.stream()
                                .map(character -> modelMapper.map(character, CharacterDTO.class))
                                .collect(Collectors.toList());
        }

        public CharacterDTO getCharacter(Long id) {
                ModelMapper modelMapper = new ModelMapper();
                Character character = characterRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Character not found with id " + id));
                return modelMapper.map(character, CharacterDTO.class);
        }

        // public CharacterDTO addCharacter(CharacterDTO characterDTO) {
        //         ModelMapper modelMapper = new ModelMapper();

        //         TypeCharacter type;
        //         Optional<TypeCharacter> typeOptional = this.typeCharacterRepository.findById(characterDTO.getId());
        //         if (typeOptional.isPresent()) {
        //                 type = TypeCharacter.builder()
        //                                 .name(characterDTO.getTypeCharacter().getName())
        //                                 .description(characterDTO.getTypeCharacter().getDescription())
        //                                 .build();
        //         } else
        //                 throw new IllegalArgumentException(
        //                                 "Power with name " + characterDTO.getTypeCharacter().getName() + " not found");

        //         Rank rank;
        //         Optional<Rank> rankOptional = this.rankRepository.findById(characterDTO.getRankCharacter().getId());
        //         if (rankOptional.isPresent()) {
        //                 rank = Rank.builder()
        //                                 .name(characterDTO.getRankCharacter().getName())
        //                                 .description(characterDTO.getRankCharacter().getDescription())
        //                                 .build();
        //         } else
        //                 throw new IllegalArgumentException(
        //                                 "Rank with name " + characterDTO.getRankCharacter().getName() + " not found");

        //         List<Power> powers = new ArrayList<>();
        //         for (int i = 0; i < characterDTO.getPowers().size(); i++) {
        //                 Optional<Power> powerOptional = this.powerRepository
        //                                 .findByName(characterDTO.getPowers().get(i).getName());
        //                 Power power;
        //                 if (powerOptional.isPresent()) {
        //                         // Skill skill;
        //                         // skill = Skill.builder()
        //                         // .name(characterDTO.getPowers().get(i).getSkill().getName())
        //                         // .description(characterDTO.getPowers().get(i).getSkill().getDescription())
        //                         // .build();
        //                         ModelMapper modelMapperSkillPower = new ModelMapper();

        //                         power = Power.builder()
        //                                         .name(powerOptional.get().getName())
        //                                         .description(powerOptional.get().getDescription())
        //                                         .skill(modelMapperSkillPower.map(powerOptional.get().getSkill(),
        //                                                         Skill.class))
        //                                         .build();
        //                         powers.add(power);
        //                 } else
        //                         throw new IllegalArgumentException("Power with name "
        //                                         + characterDTO.getPowers().get(i).getName() + " not found");
        //         }

        //         Character character = Character.builder()
        //                         .name(characterDTO.getName())
        //                         .age(characterDTO.getAge())
        //                         .gender(characterDTO.getGender())
        //                         .height(characterDTO.getHeight())
        //                         .description(characterDTO.getDescription())
        //                         .imageUrl(characterDTO.getImageUrl())
        //                         .type(type)
        //                         .rank(rank)
        //                         .powers(powers)
        //                         .build();

        //         Character savedCharacter = characterRepository.save(character);
        //         return modelMapper.map(savedCharacter, CharacterDTO.class);
        // }

        public CharacterDTO addCharacter(CharacterDTO characterDTO) {
                ModelMapper modelMapper = new ModelMapper();
            
                // Validar TypeCharacter
                TypeCharacter typeCharacter = typeCharacterRepository.findById(characterDTO.getTypeCharacter().getId())
                    .orElseThrow(() -> new IllegalArgumentException(
                        "TypeCharacter with ID " + characterDTO.getTypeCharacter().getId() + " not found"));
            
                // Validar Rank
                Rank rank = rankRepository.findById(characterDTO.getRankCharacter().getId())
                    .orElseThrow(() -> new IllegalArgumentException(
                        "Rank with ID " + characterDTO.getRankCharacter().getId() + " not found"));
            
                // Validar y agregar Powers
                List<Power> powers = new ArrayList<>();
                for (PowerDTO powerDTO : characterDTO.getPowers()) {
                    Power power = powerRepository.findById(powerDTO.getId())
                        .orElseThrow(() -> new IllegalArgumentException(
                            "Power with ID " + powerDTO.getId() + " not found"));
            
                    powers.add(power);
                }
            
                // Construir y guardar Character
                Character character = Character.builder()
                    .name(characterDTO.getName())
                    .age(characterDTO.getAge())
                    .gender(characterDTO.getGender())
                    .height(characterDTO.getHeight())
                    .description(characterDTO.getDescription())
                    .imageUrl(characterDTO.getImageUrl())
                    .type(typeCharacter)
                    .rank(rank)
                    .powers(powers)
                    .build();
            
                Character savedCharacter = characterRepository.save(character);
            
                // Devolver CharacterDTO
                return modelMapper.map(savedCharacter, CharacterDTO.class);
            }
            

        public CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO) {
                Character existingCharacter = characterRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Character not found with id " + id));

                TypeCharacter typeCharacter = typeCharacterRepository.findById(characterDTO.getTypeCharacter().getId())
                                .orElseThrow(() -> new RuntimeException(
                                                "TypeCharacter not found with id "
                                                                + characterDTO.getTypeCharacter().getId()));

                Rank rank = rankRepository.findById(characterDTO.getId())
                                .orElseThrow(() -> new RuntimeException(
                                                "Rank not found with id " + characterDTO.getRankCharacter().getId()));

                List<Power> powers = new ArrayList<>();
                for (PowerDTO powerDTO : characterDTO.getPowers()) {
                        Power power = powerRepository.findById(powerDTO.getId())
                                        .orElseThrow(() -> new RuntimeException(
                                                        "Power not found with id " + powerDTO.getId()));
                        powers.add(power);
                }

                existingCharacter.setName(characterDTO.getName());
                existingCharacter.setAge(characterDTO.getAge());
                existingCharacter.setGender(characterDTO.getGender());
                existingCharacter.setHeight(characterDTO.getHeight());
                existingCharacter.setDescription(characterDTO.getDescription());
                existingCharacter.setImageUrl(characterDTO.getImageUrl());
                existingCharacter.setType(typeCharacter);
                existingCharacter.setRank(rank);
                existingCharacter.setPowers(powers);

                Character updatedCharacter = characterRepository.save(existingCharacter);
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(updatedCharacter, CharacterDTO.class);
        }

        public void deleteCharacterById(Long id) {
                Character character = characterRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Character not found with id " + id));
                characterRepository.delete(character);
        }
}
