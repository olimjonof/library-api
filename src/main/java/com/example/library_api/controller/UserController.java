package com.example.library_api.controller;

import com.example.library_api.model.User;
import com.example.library_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping
    public User createUser(@Valid @RequestBody User user){
        return userService.createUser(user);
    }
}
