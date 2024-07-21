package com.jxtdev.knyapi.dto;

import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class PowerDTO {
    private Long id;
    @NotBlank(message = "Power cannot be null or empty")
    private String name;
    private String description;
    @Valid
    private SkillDTO skill;
}
