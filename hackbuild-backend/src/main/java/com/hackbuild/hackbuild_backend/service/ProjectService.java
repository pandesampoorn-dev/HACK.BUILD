package com.hackbuild.hackbuild_backend.service;

import com.hackbuild.hackbuild_backend.dto.ProjectRequest;
import com.hackbuild.hackbuild_backend.dto.ProjectResponse;
import com.hackbuild.hackbuild_backend.model.Project;
import com.hackbuild.hackbuild_backend.model.User;
import com.hackbuild.hackbuild_backend.repository.ProjectRepository;
import com.hackbuild.hackbuild_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepo;
    private final UserRepository userRepo;

    public ProjectService(ProjectRepository repo, UserRepository userRepo) {
        this.projectRepo = repo;
        this.userRepo = userRepo;
    }

    // ---------------------------------------------------------
    // 1 CREATE PROJECT
    // ---------------------------------------------------------
    public ProjectResponse createProject(ProjectRequest request) {

        User owner = userRepo.findById(request.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = new Project();
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setOwner(owner);

        project.setSkills(request.getSkills());

        // Default team size if invalid
        if (request.getMaxTeamSize() <= 0) {
            project.setMaxTeamSize(5);
        } else {
            project.setMaxTeamSize(request.getMaxTeamSize());
        }

        // Ensure owner is a participant
        project.getParticipants().add(owner);

        Project saved = projectRepo.save(project);
        return toResponse(saved);
    }


    // ---------------------------------------------------------
    // 2 JOIN PROJECT
    // ---------------------------------------------------------
    public ProjectResponse joinProject(Long projectId, Long userId) {

        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Stop if full
        if (project.getParticipants().size() >= project.getMaxTeamSize()) {
            throw new RuntimeException(" Team is full");
        }

        // Stop if already member
        if (project.getParticipants().contains(user)) {
            throw new RuntimeException("⚠ User already joined");
        }

        project.getParticipants().add(user);

        return toResponse(projectRepo.save(project));
    }


    // ---------------------------------------------------------
    // 3️ LEAVE PROJECT (OWNER CAN'T LEAVE)
    // ---------------------------------------------------------
    public ProjectResponse leaveProject(Long projectId, Long userId) {

        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //  prevent owner from leaving their own project
        if (project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Owner cannot leave their own project");
        }

        project.getParticipants().remove(user);

        return toResponse(projectRepo.save(project));
    }


    // ---------------------------------------------------------
    // 4️ GET ALL PROJECTS
    // ---------------------------------------------------------
    public List<ProjectResponse> getAllProjects() {
        return projectRepo.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }


    // ---------------------------------------------------------
    // 5️ DELETE PROJECT
    // ---------------------------------------------------------
    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }


    // ---------------------------------------------------------
    //  UPDATED HELPER → Structured participant response
    // ---------------------------------------------------------
    private ProjectResponse toResponse(Project project) {

        List<ProjectResponse.UserSummary> members = project.getParticipants().stream()
                .map(u -> new ProjectResponse.UserSummary(
                        u.getId(),
                        u.getName(),
                        u.getEmail()
                ))
                .distinct()
                .collect(Collectors.toList());

        return new ProjectResponse(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getOwner().getId(),
                members,
                project.getSkills(),
                project.getMaxTeamSize()
        );
    }
}
