package com.roomrent.backend.controller;

import com.roomrent.backend.model.Anuncio;
import com.roomrent.backend.service.AnuncioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anuncios")
@CrossOrigin(origins = "*")
public class AnuncioController {

    private final AnuncioService service;

    public AnuncioController(AnuncioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Anuncio> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Anuncio criar(@RequestBody Anuncio anuncio) {
        return service.salvar(anuncio);
    }
}
