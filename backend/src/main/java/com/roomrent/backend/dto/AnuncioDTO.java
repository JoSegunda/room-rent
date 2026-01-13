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
    
    // detalhes da propriedade
    private String tipoCasa;
    private Integer numQuartos;
    private Integer quartosLivres;
    private Double Area;
    private Double quartoArea;
    private String andar;
    private String genResAct;
    
    // regras da casa
    private Boolean permitido_fumar;
    private Boolean permitidoAnimais;
    private Boolean permitidoCasais;
    private Boolean permitidoVisitas;
    
    // Perfil desejado
    private Integer idadeMin;
    private Integer idadeMax;
    private String generoPrefer;
    private Integer estadiaMin;
    private List<String> ocupacaoPrefer;
    
    // Features
    private List<String> propertyFeatures;
    private List<String> roomFeatures;
    
    // colegas Info
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAdType() {
        return adType;
    }
    public void setAdType(String adType) {
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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
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
    public List<String> getOcupacaoPrefer() {
        return ocupacaoPrefer;
    }
    public void setOcupacaoPrefer(List<String> ocupacaoPrefer) {
        this.ocupacaoPrefer = ocupacaoPrefer;
    }
    public List<String> getPropertyFeatures() {
        return propertyFeatures;
    }
    public void setPropertyFeatures(List<String> propertyFeatures) {
        this.propertyFeatures = propertyFeatures;
    }
    public List<String> getRoomFeatures() {
        return roomFeatures;
    }
    public void setRoomFeatures(List<String> roomFeatures) {
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
    public List<String> getOcupacaoColegas() {
        return ocupacaoColegas;
    }
    public void setOcupacaoColegas(List<String> ocupacaoColegas) {
        this.ocupacaoColegas = ocupacaoColegas;
    }
    public List<String> getImageUrls() {
        return imageUrls;
    }
    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
    public String getMainImageUrl() {
        return mainImageUrl;
    }
    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserFullName() {
        return userFullName;
    }
    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
    public String getUserProfilePicture() {
        return userProfilePicture;
    }
    public void setUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
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
    

    // Setter e getters
    
}