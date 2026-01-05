package dev.bruno.project_demand_management.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.bruno.project_demand_management.controller.dto.ApiResponse;
import dev.bruno.project_demand_management.controller.dto.CreateProjectRequest;
import dev.bruno.project_demand_management.service.ProjectService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Void> createProject(@Valid @RequestBody CreateProjectRequest createProjectRequest) {

        var projectId = projectService.createProject(createProjectRequest);

        return ResponseEntity.created(URI.create("/projects/" + projectId)).build();
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse> listProjects(
        @RequestParam(name="pageNumber", defaultValue="0") Integer pageNumber,
        @RequestParam(name="pageSize", defaultValue="0") Integer pageSize,
        @RequestParam(name="orderBy", defaultValue="desc") String orderBy
    ) {

        var apiResponse = projectService.listProjects(
            pageNumber,
            pageSize,
            orderBy
        );

        return ResponseEntity.ok().body(apiResponse);
    }
}