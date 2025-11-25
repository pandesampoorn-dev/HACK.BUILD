package com.hackbuild.hackbuild_backend.repository;

import com.hackbuild.hackbuild_backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {}
