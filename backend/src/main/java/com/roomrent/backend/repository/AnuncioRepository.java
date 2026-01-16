package com.roomrent.backend.repository;

import com.roomrent.backend.model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

    @Query("SELECT a FROM Anuncio a WHERE " +
       "(:tipo IS NULL OR LOWER(a.tipoAnuncio) = LOWER(:tipo)) AND " +
       "(:search IS NULL OR LOWER(a.cidade) LIKE LOWER(CONCAT('%', :search, '%')) " +
       "OR LOWER(a.titulo) LIKE LOWER(CONCAT('%', :search, '%')) " +
       "OR LOWER(a.user.nome) LIKE LOWER(CONCAT('%', :search, '%')))")
        Page<Anuncio> findFiltered(
            @Param("tipo") String tipo, 
            @Param("search") String search, 
            Pageable pageable
        );
}
