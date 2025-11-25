package com.hackbuild.hackbuild_backend.service;

import com.hackbuild.hackbuild_backend.model.User;
import com.hackbuild.hackbuild_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository repo) {
        this.userRepo = repo;
    }

    // -------- SIGNUP --------
    public User register(String name, String email) {
        if (userRepo.findByEmail(email).isPresent()) {
            throw new RuntimeException("❌ User already exists!");
        }

        User newUser = new User(name, email);
        return userRepo.save(newUser);
    }

    // -------- LOGIN --------
    public User login(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("❌ User not found! Please sign up."));
    }
}
