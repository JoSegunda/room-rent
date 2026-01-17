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
import java.util.List; // Importação vital
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
            case "tamanho" -> Sort.by("area").descending();
            default -> Sort.by("id").descending();
        };

        Pageable pageable = PageRequest.of(page, size, sorting);
        return anuncioRepository.findFiltered(tipo, search, pageable);
    }

    // Parte (b): Listar anúncios do próprio utilizador
    @GetMapping("/meus-anuncios/{userId}")
    public List<Anuncio> listarPorUtilizador(@PathVariable Long userId) {
        return anuncioRepository.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<?> criarAnuncio(@RequestBody Anuncio anuncio, @RequestParam Long userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow();
            anuncio.setUser(user);
            Anuncio salvo = anuncioRepository.save(anuncio);

            // Parte (d): Obter referência MB externa
            String dadosPagamento = pagamentoService.obterReferenciaMB(5.0);

            Map<String, Object> resposta = new HashMap<>();
            resposta.put("anuncio", salvo);
            resposta.put("pagamento", dadosPagamento);

            return ResponseEntity.ok(resposta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}