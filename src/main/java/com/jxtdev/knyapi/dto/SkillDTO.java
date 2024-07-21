package com.jxtdev.knyapi.dto;

import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {
    private Long id;
    @NotBlank(message = "Skill name cannot be null or empty")
    private String name;
    private String description;
}
