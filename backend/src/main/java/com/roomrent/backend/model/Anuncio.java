package com.roomrent.backend.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Anuncios")
public class Anuncio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ad_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdType adType; // oferta ou procura
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Column(nullable = false)
    private double price;
    
    @Column(name = "moeda", nullable = false)
    private String moeda = "EUR";
    
    @Column(name = "payment_period", nullable = false)
    private String paymentPeriod = "month";
    
    // Location
    private String cidade;
    private String freguesia;
    private String endereco;
    
    @Column(name = "codPostal")
    private String codPostal;
    
    // Property Details
    @Column(name = "tipoCasa")
    private String tipoCasa; // T1, T2, T3, etc.
    
    @Column(name = "Numero_de_quartos")
    private Integer numQuartos;
    
    @Column(name = "quartosLivres")
    private Integer quartosLivres;
    
    @Column(name = "area")
    private Double Area;
    
    @Column(name = "quarto_area")
    private Double quartoArea;
    
    @Column(name = "andar")
    private String andar;
    
    @Column(name = "genero_residentes_atuais")
    private String genResAct;
    
    // House Rules
    @Column(name = "permitido_fumar")
    private Boolean permitido_fumar = false;
    
    @Column(name = "permitido_animais")
    private Boolean permitidoAnimais = false;
    
    @Column(name = "permitido_casais")
    private Boolean permitidoCasais = false;

    @Column(name = "permitido_visitas")
    private Boolean permitidoVisitas = false;

    
    // Perfil procurado
    @Column(name = "idade_min")
    private Integer idadeMin;
    
    @Column(name = "idade_max")
    private Integer idadeMax;
    
    @Column(name = "genero_preferido")
    private String generoPrefer;
    
    @Column(name = "estadia_min")
    private Integer estadiaMin = 6;
    
    @Column(name = "ocupacao_preferida")
    private String ocupacaoPrefer; // JSON string 
    
    // Características da propriedade (como JSON ou tabela separada)
    @Column(name = "property_features", columnDefinition = "TEXT")
    private String propertyFeatures; // JSON: ["wifi"]
    
    // Cracterísticas do quarto
    @Column(name = "room_feature", columnDefinition = "TEXT")
    private String roomFeatures; // JSON: ["mobilado"]
    
    // Companheiros Info
    @Column(name = "colegas_totais")
    private Integer colegasTotais;
    
    @Column(name = "idade_colegas")
    private String idadeColegas;
    
    @Column(name = "ocupacao_colegas")
    private String ocupacaoColegas;

    // Estado do anúncio
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @Column(name = "is_featured")
    private Boolean isFeatured = false;
    
    @Column(name = "view_count")
    private Integer viewCount = 0;
    
    // Timestamps
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
    
    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<imagemAnuncio> images = new HashSet<>();
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        expiresAt = LocalDateTime.now().plusMonths(3); // Expira em 3 meses
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum AdType {
        OFFER, SEARCH
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdType getAdType() {
        return adType;
    }

    public void setAdType(AdType adType) {
        this.adType = adType;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(String paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getFreguesia() {
        return freguesia;
    }

    public void setFreguesia(String freguesia) {
        this.freguesia = freguesia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getTipoCasa() {
        return tipoCasa;
    }

    public void setTipoCasa(String tipoCasa) {
        this.tipoCasa = tipoCasa;
    }

    public Integer getNumQuartos() {
        return numQuartos;
    }

    public void setNumQuartos(Integer numQuartos) {
        this.numQuartos = numQuartos;
    }

    public Integer getQuartosLivres() {
        return quartosLivres;
    }

    public void setQuartosLivres(Integer quartosLivres) {
        this.quartosLivres = quartosLivres;
    }

    public Double getArea() {
        return Area;
    }

    public void setArea(Double area) {
        Area = area;
    }

    public Double getQuartoArea() {
        return quartoArea;
    }

    public void setQuartoArea(Double quartoArea) {
        this.quartoArea = quartoArea;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public String getGenResAct() {
        return genResAct;
    }

    public void setGenResAct(String genResAct) {
        this.genResAct = genResAct;
    }

    public Boolean getPermitido_fumar() {
        return permitido_fumar;
    }

    public void setPermitido_fumar(Boolean permitido_fumar) {
        this.permitido_fumar = permitido_fumar;
    }

    public Boolean getPermitidoAnimais() {
        return permitidoAnimais;
    }

    public void setPermitidoAnimais(Boolean permitidoAnimais) {
        this.permitidoAnimais = permitidoAnimais;
    }

    public Boolean getPermitidoCasais() {
        return permitidoCasais;
    }

    public void setPermitidoCasais(Boolean permitidoCasais) {
        this.permitidoCasais = permitidoCasais;
    }

    public Boolean getPermitidoVisitas() {
        return permitidoVisitas;
    }

    public void setPermitidoVisitas(Boolean permitidoVisitas) {
        this.permitidoVisitas = permitidoVisitas;
    }

    public Integer getIdadeMin() {
        return idadeMin;
    }

    public void setIdadeMin(Integer idadeMin) {
        this.idadeMin = idadeMin;
    }

    public Integer getIdadeMax() {
        return idadeMax;
    }

    public void setIdadeMax(Integer idadeMax) {
        this.idadeMax = idadeMax;
    }

    public String getGeneroPrefer() {
        return generoPrefer;
    }

    public void setGeneroPrefer(String generoPrefer) {
        this.generoPrefer = generoPrefer;
    }

    public Integer getEstadiaMin() {
        return estadiaMin;
    }

    public void setEstadiaMin(Integer estadiaMin) {
        this.estadiaMin = estadiaMin;
    }

    public String getOcupacaoPrefer() {
        return ocupacaoPrefer;
    }

    public void setOcupacaoPrefer(String ocupacaoPrefer) {
        this.ocupacaoPrefer = ocupacaoPrefer;
    }

    public String getPropertyFeatures() {
        return propertyFeatures;
    }

    public void setPropertyFeatures(String propertyFeatures) {
        this.propertyFeatures = propertyFeatures;
    }

    public String getRoomFeatures() {
        return roomFeatures;
    }

    public void setRoomFeatures(String roomFeatures) {
        this.roomFeatures = roomFeatures;
    }

    public Integer getColegasTotais() {
        return colegasTotais;
    }

    public void setColegasTotais(Integer colegasTotais) {
        this.colegasTotais = colegasTotais;
    }

    public String getIdadeColegas() {
        return idadeColegas;
    }

    public void setIdadeColegas(String idadeColegas) {
        this.idadeColegas = idadeColegas;
    }

    public String getOcupacaoColegas() {
        return ocupacaoColegas;
    }

    public void setOcupacaoColegas(String ocupacaoColegas) {
        this.ocupacaoColegas = ocupacaoColegas;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<imagemAnuncio> getImages() {
        return images;
    }

    public void setImages(Set<imagemAnuncio> images) {
        this.images = images;
    }
    
    // Getters and Setters
    
    
}