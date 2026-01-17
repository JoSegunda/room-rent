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
        System.out.println("Tentativa de login para o email: " + loginRequest.getEmail());

        return userRepository.findByEmail(loginRequest.getEmail())
            .map(user -> {
                // Comparar a password enviada com a da base de dados
                boolean passwordCorreta = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
                
                if (passwordCorreta) {
                    // VERIFICAÇÃO DE APROVAÇÃO
                    if (!"ADMIN".equals(user.getRole()) && !user.isAprovado()) {
                        System.out.println("Login bloqueado: Utilizador não aprovado.");
                        return ResponseEntity.status(403).body("A sua conta ainda não foi aprovada.");
                    }

                    System.out.println("Login bem-sucedido para: " + user.getNome());
                    user.setPassword(null); // Segurança
                    return ResponseEntity.ok(user);
                } else {
                    System.out.println("Falha: Password incorreta para o utilizador.");
                    return ResponseEntity.status(401).body("Palavra-passe incorreta.");
                }
            })
            .orElseGet(() -> {
                System.out.println("Falha: Email não encontrado.");
                return ResponseEntity.status(401).body("Utilizador não encontrado.");
            });
    }
}