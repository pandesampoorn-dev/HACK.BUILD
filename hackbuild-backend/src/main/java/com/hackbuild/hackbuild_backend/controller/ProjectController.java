package com.hackbuild.hackbuild_backend.controller;

import com.hackbuild.hackbuild_backend.dto.ProjectRequest;
import com.hackbuild.hackbuild_backend.dto.ProjectResponse;
import com.hackbuild.hackbuild_backend.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService service) {
        this.projectService = service;
    }

    @PostMapping
    public ProjectResponse createProject(@RequestBody ProjectRequest request) {
        return projectService.createProject(request);
    }

    @GetMapping
    public List<ProjectResponse> getAll() {
        return projectService.getAllProjects();
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }

    @PostMapping("/{projectId}/join/{userId}")
    public ProjectResponse join(@PathVariable Long projectId, @PathVariable Long userId) {
        return projectService.joinProject(projectId, userId);
    }

    @PostMapping("/{projectId}/leave/{userId}")
    public ProjectResponse leave(@PathVariable Long projectId, @PathVariable Long userId) {
        return projectService.leaveProject(projectId, userId);
    }
}
