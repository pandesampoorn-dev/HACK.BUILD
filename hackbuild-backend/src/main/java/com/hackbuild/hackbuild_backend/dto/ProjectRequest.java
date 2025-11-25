package com.hackbuild.hackbuild_backend.dto;

import java.util.List;

public class ProjectRequest {

    private String title;
    private String description;
    private Long ownerId;

    // New fields
    private List<String> skills;
    private int maxTeamSize;

    public ProjectRequest() {}

    // ---------- GETTERS ----------

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public List<String> getSkills() {
        return skills;
    }

    public int getMaxTeamSize() {
        return maxTeamSize;
    }
}
