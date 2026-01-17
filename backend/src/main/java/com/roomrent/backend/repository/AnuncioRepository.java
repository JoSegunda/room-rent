package com.roomrent.backend.repository;

import com.roomrent.backend.model.Anuncio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

    // MODIFICAÇÃO: Adicionada a verificação "a.ativo = true" no início da Query
    @Query("SELECT a FROM Anuncio a WHERE a.ativo = true AND " +
           "(:tipo IS NULL OR :tipo = '' OR LOWER(a.tipoAnuncio) = LOWER(:tipo)) AND " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(a.cidade) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.titulo) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.user.nome) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Anuncio> findFilteredAtivos(
        @Param("tipo") String tipo, 
        @Param("search") String search, 
        Pageable pageable
    );

    // Mantemos este para o perfil do utilizador (vê os seus próprios anúncios mesmo inativos)
    List<Anuncio> findByUserId(Long userId);

    // Corrigido o erro "age" para "Page"
    Page<Anuncio> findByAtivoTrue(Pageable pageable);

    // Método alternativo via Spring Data (útil para filtros simples)
    Page<Anuncio> findByAtivoTrueAndTipoAnuncioContainingAndTituloContaining(
        String tipo, String titulo, Pageable pageable);
}