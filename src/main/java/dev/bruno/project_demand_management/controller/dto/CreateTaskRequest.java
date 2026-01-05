package dev.bruno.project_demand_management.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import dev.bruno.project_demand_management.util.PriorityEnum;
import dev.bruno.project_demand_management.util.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest(
    UUID projectId,
    @NotBlank(message = "O parametro title não pode estar vazio!")
    @Size(min = 5, max = 150, message="O parametro title deve ter entre 5 a 150 caracteres!")
    String title,
    String description,
    StatusEnum status,
    PriorityEnum priority,
    LocalDate dueDate
) {}