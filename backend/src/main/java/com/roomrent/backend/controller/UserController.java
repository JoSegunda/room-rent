package com.roomrent.backend.controller;

import com.roomrent.backend.model.User;
import com.roomrent.backend.repository.UserRepository;
import com.roomrent.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User novoUser = userService.registrarUtilizador(user);
            return ResponseEntity.ok(novoUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint de Login (POST para os dados não aparecerem na URL)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        return userRepository.findByEmail(loginRequest.getEmail())
            .map(user -> {
                if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                    user.setPassword(null); // Segurança: não devolver a senha encriptada
                    return ResponseEntity.ok(user);
                }
                return ResponseEntity.status(401).body("Senha incorreta.");
            })
            .orElse(ResponseEntity.status(401).body("Utilizador não encontrado."));
    }
}