package com.roomrent.backend.service;

import com.roomrent.backend.model.User;
import com.roomrent.backend.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email já registado");
        }
        return userRepository.save(user);
    }
}
