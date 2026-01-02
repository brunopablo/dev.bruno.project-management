package dev.bruno.project_demand_management.controller.dto;

import java.util.List;

public record ApiResponse<T>(
    List<T> data,
    PaginationResponse paginationResponse
){}
