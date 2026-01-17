package com.roomrent.backend.controller;

import com.roomrent.backend.model.Anuncio;
import com.roomrent.backend.model.User;
import com.roomrent.backend.repository.AnuncioRepository;
import com.roomrent.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired 
    private UserRepository userRepository;

    @Autowired 
    private AnuncioRepository anuncioRepository;

    // Estatísticas para o Dashboard
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalAds", anuncioRepository.count());
        stats.put("totalUsers", userRepository.count());
        stats.put("pendingUsers", userRepository.findAll().stream().filter(u -> !u.isAprovado()).count());
        return ResponseEntity.ok(stats);
    }

    // Listar Utilizadores Pendentes (Aprovação de conta)
    @GetMapping("/users/pendentes")
    public List<User> listarUsersPendentes() {
        return userRepository.findAll().stream()
                .filter(u -> !u.isAprovado())
                .collect(Collectors.toList());
    }

    // Listar Anúncios Pendentes (Validação de conteúdo)
    @GetMapping("/anuncios/pendentes")
    public List<Anuncio> listarAnunciosPendentes() {
        return anuncioRepository.findAll().stream()
                .filter(a -> !a.isAtivo())
                .collect(Collectors.toList());
    }

    // Ações de Aprovação
    @PutMapping("/users/{id}/aprovar")
    @Transactional
    public ResponseEntity<?> aprovarUser(@PathVariable Long id) {
        return userRepository.findById(id).map(u -> {
            u.setAprovado(true);
            userRepository.saveAndFlush(u);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
    @Transactional
    @PutMapping("/anuncios/{id}/ativar")
    public ResponseEntity<?> ativarAnuncio(@PathVariable Long id) {
        return anuncioRepository.findById(id).map(a -> {
            a.setAtivo(true);
            anuncioRepository.save(a);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}