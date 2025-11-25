package com.hackbuild.hackbuild_backend.controller;

import com.hackbuild.hackbuild_backend.dto.UserResponse;
import com.hackbuild.hackbuild_backend.model.Project;
import com.hackbuild.hackbuild_backend.model.User;
import com.hackbuild.hackbuild_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //  REGISTER a new user (OR return existing user if email already used)
    @PostMapping("/register")
    public User register(@RequestBody User requestUser) {
        return userRepository.findByEmail(requestUser.getEmail())
                .orElseGet(() -> userRepository.save(requestUser));
    }

    //  LOGIN (returns the user with ID)
    @PostMapping("/login")
    public User login(@RequestBody User loginUser) {
        return userRepository.findByEmail(loginUser.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    //  GET all users with their joined projects
    @GetMapping
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll().stream().map(user -> {

            List<String> projectNames = user.getProjects()
                    .stream()
                    .map(Project::getTitle)
                    .collect(Collectors.toList());

            return new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    projectNames
            );

        }).collect(Collectors.toList());
    }
}
