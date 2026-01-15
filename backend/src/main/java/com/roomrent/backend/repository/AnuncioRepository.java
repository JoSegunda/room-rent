package com.roomrent.backend.repository;

import com.roomrent.backend.model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
}
