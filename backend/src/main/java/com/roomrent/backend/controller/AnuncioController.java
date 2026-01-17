package com.roomrent.backend.controller;

import com.roomrent.backend.model.Anuncio;
import com.roomrent.backend.model.User;
import com.roomrent.backend.repository.AnuncioRepository;
import com.roomrent.backend.repository.UserRepository;
import com.roomrent.backend.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/anuncios")
@CrossOrigin(origins = "*")
public class AnuncioController {

    private final AnuncioRepository anuncioRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PagamentoService pagamentoService;

    public AnuncioController(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }

    @GetMapping("/paginado")
    public Page<Anuncio> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "recentes") String sort) {

        Sort sorting = switch (sort) {
            case "baratos" -> Sort.by("preco").ascending();
            case "caro" -> Sort.by("preco").descending();
            case "tamanho" -> Sort.by("areaImovel").descending(); // Ajustado para coincidir com o campo da entidade
            default -> Sort.by("id").descending();
        };

        Pageable pageable = PageRequest.of(page, size, sorting);
        
        // MODIFICAÇÃO: Chamamos um novo método que filtra apenas por anúncios ATIVOS
        return anuncioRepository.findFilteredAtivos(tipo, search, pageable);
    }

    @GetMapping("/meus-anuncios/{userId}")
    public List<Anuncio> listarPorUtilizador(@PathVariable Long userId) {
        // O utilizador deve poder ver os seus próprios anúncios no perfil, mesmo inativos
        return anuncioRepository.findByUserId(userId);
    }

    // Localização: src/main/java/com/roomrent/backend/controller/AnuncioController.java

    @PostMapping
    public ResponseEntity<?> criarAnuncio(@RequestBody Anuncio anuncio, @RequestParam Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

            // BLOQUEIO: Verificar se o utilizador está aprovado
            if (!user.isAprovado()) {
                return ResponseEntity.status(403)
                        .body("A sua conta ainda não foi aprovada pelo administrador. Não pode publicar anúncios.");
            }

            anuncio.setUser(user);
            anuncio.setAtivo(false); // Nasce inativo para validação do admin
            
            Anuncio salvo = anuncioRepository.save(anuncio);
            String dadosPagamento = pagamentoService.obterReferenciaMB(5.0);

            Map<String, Object> resposta = new HashMap<>();
            resposta.put("anuncio", salvo);
            resposta.put("pagamento", dadosPagamento);

            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anuncio> obterPorId(@PathVariable Long id) {
        return anuncioRepository.findById(id)
                .map(ad -> {
                    // Opcional: Impedir acesso direto via URL se o anúncio não estiver ativo
                    if (!ad.isAtivo()) return ResponseEntity.notFound().<Anuncio>build();
                    return ResponseEntity.ok(ad);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}