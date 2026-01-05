package dev.bruno.project_demand_management.util.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.bruno.project_demand_management.controller.dto.PaginationResponse;
import dev.bruno.project_demand_management.controller.dto.ProjectResponse;
import dev.bruno.project_demand_management.controller.dto.TaskResponse;
import dev.bruno.project_demand_management.entity.ProjectEntity;
import dev.bruno.project_demand_management.entity.TaskEntity;

@Component
public class DoMapper {

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
    
    public List<ProjectResponse> toListProjectResponse(List<ProjectEntity> pageContent) {
    
        return pageContent.stream().map(
            content -> new ProjectResponse(
                content.getName(),
                content.getDescription(),
                content.getStartDate(),
                content.getEndDate()
            )
        ).toList();
    }
    
    public PaginationResponse toPaginationResponse(
        Integer pageNumber,
        Integer pageSize,
        Long totalElements,
        Integer totalPages
    ) {
    
        return new PaginationResponse(
            pageNumber,
            pageSize,
            totalElements,
            totalPages                        
        );
    }
}