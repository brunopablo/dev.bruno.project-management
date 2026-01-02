package dev.bruno.project_demand_management.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dev.bruno.project_demand_management.controller.dto.ApiResponse;
import dev.bruno.project_demand_management.controller.dto.CreateProjectRequest;
import dev.bruno.project_demand_management.controller.dto.ListProjectsResponse;
import dev.bruno.project_demand_management.controller.dto.PaginationResponse;
import dev.bruno.project_demand_management.entity.ProjectEntity;
import dev.bruno.project_demand_management.repository.ProjectRepository;

@Service
public class ProjectService {
    
    private ProjectRepository projectRepository;
    
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    
    public UUID createProject(CreateProjectRequest createProjectRequest) {
        
        var projectEntity = projectRepository.save(
            new ProjectEntity(
                null,
                createProjectRequest.name(),     
                createProjectRequest.description(),     
                createProjectRequest.startDate(),     
                createProjectRequest.endDate(),
                null     
            )
        );
        
        return projectEntity.getId();
    }
    
    public ApiResponse listProjects(
            Integer pageNumber,
            Integer pageSize,
            String orderBy) {

        var pageRequest = getPageRequest(pageNumber, pageSize, orderBy);

        var page = getPageContent(pageRequest);
        
        var apiResponse = new ApiResponse<>(
            page.getContent().stream().map(
                content -> new ListProjectsResponse(
                    content.getName(),
                    content.getDescription(),
                    content.getStartDate(),
                    content.getEndDate()
                )
            ).toList(),
            new PaginationResponse(
                page.getNumber(),    
                page.getSize(),    
                page.getTotalElements(),    
                page.getTotalPages()    
            )
        );

        return apiResponse;
    }

    private Page<ProjectEntity> getPageContent(PageRequest pageRequest) {
        
        return projectRepository.findAll(pageRequest);
    }

    private PageRequest getPageRequest(
            Integer pageNumber,
            Integer pageSize,
            String orderBy) {

        Direction orderByDirection = Sort.Direction.DESC;

        if (orderBy.equalsIgnoreCase("asc"))
            orderByDirection = Sort.Direction.ASC;

        
        return PageRequest.of(
            pageNumber,
            pageSize,
            orderByDirection,
            "name"
        );
    }
}