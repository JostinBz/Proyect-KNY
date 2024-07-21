package com.jxtdev.knyapi.dto;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDTO {
    private Long id;
    @NotBlank(message = "Type cannot be null or empty")
    private String name;
    @NotNull(message = "Age cannot be null")
    @Min(value = 0, message = "Age must be equal or greater than 0")
    private int age;
    @NotBlank(message = "Type cannot be null or empty")
    private String gender;
    private double height;
    private String description;
    private String imageUrl;
    @Valid
    private TypeCharacterDTO typeCharacter;
    @Valid
    private RankDTO rankCharacter;
    @Valid
    private List<PowerDTO> powers;
}