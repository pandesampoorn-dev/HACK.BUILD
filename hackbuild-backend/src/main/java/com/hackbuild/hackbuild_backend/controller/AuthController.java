package com.hackbuild.hackbuild_backend.controller;

import com.hackbuild.hackbuild_backend.dto.LoginRequest;
import com.hackbuild.hackbuild_backend.dto.RegisterRequest;
import com.hackbuild.hackbuild_backend.model.User;
import com.hackbuild.hackbuild_backend.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ------------ SIGNUP --------------
    @PostMapping("/signup")
    public User signup(@RequestBody RegisterRequest request) {
        return userService.register(request.getName(), request.getEmail());
    }

    // ------------ LOGIN --------------
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return userService.login(request.getEmail());
    }

    @GetMapping("/test")
    public String test() {
        return "Auth Controller Loaded";
    }
}
