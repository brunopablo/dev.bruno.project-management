package dev.bruno.project_demand_management.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import dev.bruno.project_demand_management.util.PriorityEnum;
import dev.bruno.project_demand_management.util.StatusEnum;

public record TaskResponse(
    UUID projectId,
    String title,
    String description,
    StatusEnum status,
    PriorityEnum priority,
    LocalDate dueDate
) {}
