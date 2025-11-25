package com.hackbuild.hackbuild_backend.dto;

import java.util.List;

public class ProjectResponse {

    private Long id;
    private String title;
    private String description;
    private Long ownerId;

    //  Updated: return structured participant info instead of just names
    private List<UserSummary> participants;

    private List<String> skills;
    private int maxTeamSize;

    public ProjectResponse(Long id, String title, String description, Long ownerId,
                           List<UserSummary> participants, List<String> skills, int maxTeamSize) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
        this.participants = participants;
        this.skills = skills;
        this.maxTeamSize = maxTeamSize;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Long getOwnerId() { return ownerId; }
    public List<UserSummary> getParticipants() { return participants; }
    public List<String> getSkills() { return skills; }
    public int getMaxTeamSize() { return maxTeamSize; }

    //  Nested DTO
    public static class UserSummary {
        private Long id;
        private String name;
        private String email;

        public UserSummary(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public Long getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
    }
}
