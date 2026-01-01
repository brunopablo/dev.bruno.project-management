package dev.bruno.project_demand_management.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.bruno.project_demand_management.controller.dto.CreateProjectRequest;
import dev.bruno.project_demand_management.service.ProjectService;
import jakarta.validation.Valid;

@Controller()
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Void> createProject(@Valid @RequestBody CreateProjectRequest createProjectRequest) {
        
        IO.println(createProjectRequest.name());

        var projectId = projectService.createProject(createProjectRequest);

        return ResponseEntity.created(URI.create("/projects" + projectId)).build();
    }
}
