package com.jxtdev.knyapi.dto;

import java.util.List;
import lombok.Data;

@Data
public class CharacterDTO {
    private String name;
    private String description;
    private String imageUrl;
    private TypeCharacterDTO typeCharacter;
    private RankDTO rankCharacter;
    private List<PowerDTO> powers;
}