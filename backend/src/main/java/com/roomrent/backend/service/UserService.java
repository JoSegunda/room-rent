package com.roomrent.backend.service;

import com.roomrent.backend.model.User;
import com.roomrent.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Método que faltava para resolver o erro de compilação
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User registrarUtilizador(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Este email já está registado!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}