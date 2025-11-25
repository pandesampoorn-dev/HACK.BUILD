package com.hackbuild.hackbuild_backend.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    //  Add this relation (reverse link from Project participants)
    @ManyToMany(mappedBy = "participants")
    private Set<Project> projects = new HashSet<>();

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // -------- Getters & Setters ----------

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
