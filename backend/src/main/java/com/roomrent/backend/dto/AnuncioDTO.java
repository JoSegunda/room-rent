package com.roomrent.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AnuncioDTO {
    private Long id;
    private String adType; // "OFFER" or "SEARCH"
    private String titulo;
    private String descricao;
    private Double price;
    private String moeda;
    private String paymentPeriod;
    
    // Localização
    private String cidade;
    private String freguesia;
    private String endereco;
    private String codPostal;
    
    // Property Details
    private String tipoCasa;
    private Integer numQuartos;
    private Integer quartosLivres;
    private Double Area;
    private Double quartoArea;
    private String andar;
    private String genResAct;
    
    // House Rules
    private Boolean permitido_fumar;
    private Boolean permitidoAnimais;
    private Boolean permitidoCasais;
    private Boolean permitidoVisitas;
    
    // Desired Profile
    private Integer idadeMin;
    private Integer idadeMax;
    private String generoPrefer;
    private Integer estadiaMin;
    private List<String> ocupacaoPrefer;
    
    // Features
    private List<String> propertyFeatures;
    private List<String> roomFeatures;
    
    // Roommate Info
    private Integer colegasTotais;
    private String idadeColegas;
    private List<String> ocupacaoColegas;
    
    // Imagens
    private List<String> imageUrls;
    private String mainImageUrl;
    
    // User Info
    private Long userId;
    private String userFullName;
    private String userProfilePicture;
    
    // Tempos
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    
}