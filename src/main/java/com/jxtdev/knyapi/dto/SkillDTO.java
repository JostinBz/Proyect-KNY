package com.jxtdev.knyapi.dto;

import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Skill")
public class SkillDTO {
    private Long id;
    @NotBlank(message = "Skill name cannot be null or empty")
    private String name;
    private String description;
}
