package com.jxtdev.knyapi.dto;

import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Rank")
public class RankDTO {
    private Long id;
    @NotBlank(message = "Rank name cannot be null or empty")
    private String name;
    private String description;
}
