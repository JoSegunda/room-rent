package com.roomrent.backend.service;

import com.roomrent.backend.model.Anuncio;
import com.roomrent.backend.repository.AnuncioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncioService {

    private final AnuncioRepository repository;

    public AnuncioService(AnuncioRepository repository) {
        this.repository = repository;
    }

    public List<Anuncio> listarTodos() {
        return repository.findAll();
    }

    public Anuncio salvar(Anuncio anuncio) {
        return repository.save(anuncio);
    }
}
