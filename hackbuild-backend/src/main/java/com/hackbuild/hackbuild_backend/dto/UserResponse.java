package com.hackbuild.hackbuild_backend.dto;

import java.util.List;

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private List<String> projects;

    public UserResponse(Long id, String name, String email, List<String> projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.projects = projects;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<String> getProjects() { return projects; }
}
