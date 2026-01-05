package dev.bruno.project_demand_management.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.bruno.project_demand_management.controller.dto.ApiResponse;
import dev.bruno.project_demand_management.controller.dto.CreateTaskRequest;
import dev.bruno.project_demand_management.service.TaskService;
import dev.bruno.project_demand_management.util.enums.PriorityEnum;
import dev.bruno.project_demand_management.util.enums.StatusEnum;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<String> createTask(@Valid @RequestBody CreateTaskRequest createTaskRequest) {

        var response = taskService.createTask(createTaskRequest);

        return ResponseEntity.created(URI.create("/task/" + response)).build();
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse> listTask(
        @RequestParam(value="pageNumber", defaultValue="0") Integer pageNumber,
        @RequestParam(value="pageSize", defaultValue="10") Integer pageSize,
        @RequestParam(value="orderBy", defaultValue="desc") String orderBy,
        @RequestParam(value="status", required=false) StatusEnum status,
        @RequestParam(value="priority", required=false) PriorityEnum priority,
        @RequestParam(value="projectId", required=false) UUID projectId
    ) {
        var apiResponse = taskService.listTask(
            pageNumber,
            pageSize,
            orderBy,
            status,
            priority,
            projectId
        );

        return ResponseEntity.ok().body(apiResponse);
    }
}