package dev.bruno.project_demand_management.controller.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CreateProjectRequest(
        @NotBlank(message = "Parametro name não pode estar vazio!")
        @Size(min=3, message="O parametro name deve possuir pelo menos 3 caracteres!")
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate
                
) {
}
