package dev.bruno.project_demand_management.util.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.bruno.project_demand_management.controller.dto.TaskResponse;
import dev.bruno.project_demand_management.entity.TaskEntity;

@Component
public class TaskMapper {

    public List<TaskResponse> toListTaskResponse(List<TaskEntity> pageContent) {

        return pageContent.stream().map(
            content -> new TaskResponse(
                content.getProjectEntity().getId(),
                content.getTitle(),
                content.getDescription(),
                content.getStatus(),
                content.getPriority(),
                content.getDueDate()
            )
        ).toList();
    }
}