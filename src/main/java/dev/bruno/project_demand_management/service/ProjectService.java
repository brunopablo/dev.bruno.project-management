package dev.bruno.project_demand_management.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dev.bruno.project_demand_management.controller.dto.ApiResponse;
import dev.bruno.project_demand_management.controller.dto.CreateProjectRequest;
import dev.bruno.project_demand_management.entity.ProjectEntity;
import dev.bruno.project_demand_management.repository.ProjectRepository;
import dev.bruno.project_demand_management.util.mapper.DoMapper;

@Service
public class ProjectService {
    
    private final ProjectRepository projectRepository;
    private final DoMapper doMapper;
    
    public ProjectService(ProjectRepository projectRepository, DoMapper doMapper) {
        this.projectRepository = projectRepository;
        this.doMapper = doMapper;
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

        var pages = getPageContent(pageRequest);
        
        var apiResponse = new ApiResponse<>(
            doMapper.toListProjectResponse(pages.getContent()),
            doMapper.toPaginationResponse(
                pages.getNumber(),    
                pages.getSize(),    
                pages.getTotalElements(),    
                pages.getTotalPages()
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