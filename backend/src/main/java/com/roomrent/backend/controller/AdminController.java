package com.roomrent.backend.controller;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired private UserRepository userRepository;
    @Autowired private AnuncioRepository anuncioRepository;

    // Listar utilizadores pendentes de aprovação
    @GetMapping("/users/pendentes")
    public List<User> listarPendentes() {
        return userRepository.findAll().stream().filter(u -> !u.isAprovado()).toList();
    }

    // Aprovar utilizador
    @PutMapping("/users/{id}/aprovar")
    public ResponseEntity<?> aprovarUser(@PathVariable Long id) {
        User u = userRepository.findById(id).orElseThrow();
        u.setAprovado(true);
        userRepository.save(u);
        return ResponseEntity.ok().build();
    }

    // Listar todos os anúncios (para ativar/inativar)
    @GetMapping("/anuncios")
    public List<Anuncio> listarTodosAnuncios() {
        return anuncioRepository.findAll();
    }

    // Alternar estado do anúncio (Ativar/Inativar)
    @PutMapping("/anuncios/{id}/toggle")
    public ResponseEntity<?> toggleAnuncio(@PathVariable Long id) {
        Anuncio a = anuncioRepository.findById(id).orElseThrow();
        a.setAtivo(!a.isAtivo());
        anuncioRepository.save(a);
        return ResponseEntity.ok().build();
    }
}