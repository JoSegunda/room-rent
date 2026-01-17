package com.roomrent.backend.controller;

import com.roomrent.backend.model.Mensagem;
import com.roomrent.backend.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mensagens")
@CrossOrigin(origins = "*")
public class MensagemController {
    
    @Autowired
    private MensagemRepository mensagemRepository;

    @PostMapping
    public ResponseEntity<Mensagem> enviarMensagem(@RequestBody Mensagem mensagem) {
        return ResponseEntity.ok(mensagemRepository.save(mensagem));
    }

    @GetMapping("/anuncio/{anuncioId}")
    public List<Mensagem> verMensagensDoAnuncio(@PathVariable Long anuncioId) {
        return mensagemRepository.findByAnuncioId(anuncioId);
    }
}