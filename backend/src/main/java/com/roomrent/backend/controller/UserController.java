package com.roomrent.backend.controller;

import com.roomrent.backend.model.User;
import com.roomrent.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000") // IMPORTANTE: Permite que o Front-end aceda à API
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        // Assume que tens um método findAll() no teu UserService
        return userService.findAll(); 
    }
}