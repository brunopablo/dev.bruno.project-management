package dev.bruno.project_demand_management.controller.dto;

public record PaginationResponse(
    Integer pageNumber,
    Integer pageSize,
    Long totalElements,
    Integer totalPages
) {}