package com.roomrent.backend.controller;

import com.roomrent.backend.model.Anuncio;
import com.roomrent.backend.repository.AnuncioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Para pagination

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@RestController
@RequestMapping("/api/anuncios")
@CrossOrigin(origins = "*")
public class AnuncioController {

    private final AnuncioRepository anuncioRepository;

    public AnuncioController(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }

    @GetMapping
    public List<Anuncio> listarTodos() {
        return anuncioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Anuncio> criarAnuncio(@RequestBody Anuncio anuncio) {
        try {
            Anuncio salvo = anuncioRepository.save(anuncio);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            e.printStackTrace(); // Isto vai mostrar o erro exato no terminal se falhar
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/paginado")
public Page<Anuncio> listarPaginado(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "4") int size,
        @RequestParam(required = false) String tipo,
        @RequestParam(required = false) String search,
        @RequestParam(defaultValue = "recentes") String sort) {

    // Lógica de Ordenação
    Sort sorting = switch (sort) {
        case "baratos" -> Sort.by("preco").ascending();
        case "caro" -> Sort.by("preco").descending();
        case "tamanho" -> Sort.by("area").descending();
        default -> Sort.by("id").descending();
    };

    Pageable pageable = PageRequest.of(page, size, sorting);
    
    // Se "tipo" vier vazio do front, tratamos como null
    String tipoFiltro = (tipo != null && !tipo.isEmpty()) ? tipo : null;
    String searchFiltro = (search != null && !search.isEmpty()) ? search : null;

    return anuncioRepository.findFiltered(tipoFiltro, searchFiltro, pageable);
}

    
}