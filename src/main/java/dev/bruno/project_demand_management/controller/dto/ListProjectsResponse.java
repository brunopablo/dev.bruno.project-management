package dev.bruno.project_demand_management.controller.dto;

import java.time.LocalDate;

public record ListProjectsResponse(
    String name,
    String description,
    LocalDate startDate,
    LocalDate endDate
) {}