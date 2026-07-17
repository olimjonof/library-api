package com.example.library_api.service;

import com.example.library_api.model.User;
import com.example.library_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Bu email bilan allaqachon ro'yxatdan o'tilgan!");
        }

        return userRepository.save(user);
    }




}
