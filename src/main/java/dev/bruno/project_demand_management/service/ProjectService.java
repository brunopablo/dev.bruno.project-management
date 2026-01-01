package dev.bruno.project_demand_management.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.bruno.project_demand_management.controller.dto.CreateProjectRequest;
import dev.bruno.project_demand_management.controller.repository.ProjectRepository;
import dev.bruno.project_demand_management.entity.ProjectEntity;

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
}