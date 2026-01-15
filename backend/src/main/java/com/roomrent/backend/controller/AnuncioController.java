package com.roomrent.backend.controller;

import com.roomrent.backend.dto.AnuncioRequestDTO;
import com.roomrent.backend.model.Anuncio;
import com.roomrent.backend.service.AnuncioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/anuncios")
@CrossOrigin(origins = "*")
public class AnuncioController {

    private final AnuncioService service;

    public AnuncioController(AnuncioService service) {
        this.service = service;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Anuncio> criarAnuncio(
            @RequestPart("dados") AnuncioRequestDTO dto,
            @RequestPart(value = "fotos", required = false) MultipartFile[] fotos
    ) throws Exception {

        return ResponseEntity.ok(service.criarAnuncio(dto, fotos));
    }
}
