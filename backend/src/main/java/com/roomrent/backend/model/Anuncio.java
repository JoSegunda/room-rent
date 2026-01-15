package com.roomrent.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "anuncios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // procura ou oferta
    @Column(nullable = false)
    private String tipoAnuncio;

    // futuramente virá do user autenticado
    private Long userId;

    // ===== LOCALIZAÇÃO =====
    private String cidade;
    private String enderecoCompleto;
    private String codigoPostal;

    // ===== QUARTO =====
    private Double preco;
    private Integer quartosDisponiveis;

    // ===== PERFIL =====
    private Integer idadeMinima;
    private Integer idadeMaxima;
    private String genero;

    // ===== IMÓVEL =====
    private Double areaImovel;
    private String tipologia;
    private Integer andar;

    // ===== DESCRIÇÃO =====
    private String titulo;

    @Column(length = 2000)
    private String descricao;

    // Caminhos das fotos
    @ElementCollection
    @CollectionTable(name = "anuncio_fotos", joinColumns = @JoinColumn(name = "anuncio_id"))
    @Column(name = "foto_path")
    private List<String> fotos;
}
