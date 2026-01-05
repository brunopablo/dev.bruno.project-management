package dev.bruno.project_demand_management.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.bruno.project_demand_management.controller.dto.ApiResponse;
import dev.bruno.project_demand_management.controller.dto.CreateTaskRequest;
import dev.bruno.project_demand_management.entity.ProjectEntity;
import dev.bruno.project_demand_management.entity.TaskEntity;
import dev.bruno.project_demand_management.repository.ProjectRepository;
import dev.bruno.project_demand_management.repository.TaskRepository;
import dev.bruno.project_demand_management.util.enums.PriorityEnum;
import dev.bruno.project_demand_management.util.enums.StatusEnum;
import dev.bruno.project_demand_management.util.mapper.DoMapper;

@Service
public class TaskService {
    
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final DoMapper doMapper;

    public TaskService(ProjectRepository projectRepository, TaskRepository taskRepository, DoMapper taskMapper) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.doMapper = taskMapper;
    }

    public Long createTask(CreateTaskRequest createTaskRequest) {
        
        var projectEntity = projectRepository.findById(createTaskRequest.projectId()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não Encontrado"));
        
        var taskEntity = taskRepository.save(
            new TaskEntity(
                null,
                createTaskRequest.title(),
                createTaskRequest.description(),
                createTaskRequest.status(),
                createTaskRequest.priority(),
                createTaskRequest.dueDate(),
                projectEntity
            )
        );
        
        return taskEntity.getId();
    }

    public ApiResponse listTask(
        Integer pageNumber,
        Integer pageSize,
        String orderBy,
        StatusEnum status,
        PriorityEnum priority,
        UUID projectId
    ) {

        var pageRequest = getPageRequest(pageNumber, pageSize, orderBy);

        var pages = getPages(status, priority, projectId, pageRequest);

        var apiResponse = new ApiResponse<>(
            doMapper.toListTaskResponse(pages.getContent()),
            doMapper.toPaginationResponse(
                pages.getNumber(),    
                pages.getSize(),    
                pages.getTotalElements(),    
                pages.getTotalPages()
            )
        );

        return apiResponse;
	}

    private Page<TaskEntity> getPages(
        StatusEnum status,
        PriorityEnum priority,
        UUID projectId,
        PageRequest pageRequest
    ) {

        ProjectEntity projectEntity = null;

        if (projectId != null) {
            projectEntity = projectRepository.findById(projectId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto com ID=" + projectId + " não encontrado")
            );
        }
        
        return taskRepository.findTasks(status, priority, projectEntity, pageRequest);
    }

    private PageRequest getPageRequest(Integer pageNumber, Integer pageSize, String orderBy) {
        
        Direction orderByDirection = Sort.Direction.DESC;

        if(orderBy.equalsIgnoreCase("asc"))
            orderByDirection = Sort.Direction.ASC;

        return PageRequest.of(pageNumber, pageSize, orderByDirection, "title");
    } 
}