package dev.bruno.project_demand_management.controller.dto;

import dev.bruno.project_demand_management.util.enums.StatusEnum;

public record UpdateStatusRequest(StatusEnum status) {

}
