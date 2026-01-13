package com.roomrent.backend.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "advertisements")
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
    private String propertyType; // T1, T2, T3, etc.
    
    @Column(name = "total_rooms")
    private Integer totalRooms;
    
    @Column(name = "quartosLivres")
    private Integer quartosLivres;
    
    @Column(name = "area")
    private Double Area;
    
    @Column(name = "quarto_area")
    private Double quartoArea;
    
    @Column(name = "anda")
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
    
    // Desired Profile
    @Column(name = "min_age")
    private Integer minAge;
    
    @Column(name = "max_age")
    private Integer maxAge;
    
    @Column(name = "preferred_gender")
    private String preferredGender;
    
    @Column(name = "min_stay_months")
    private Integer minStayMonths = 6;
    
    @Column(name = "availability_date")
    private LocalDate availabilityDate;
    
    @Column(name = "preferred_occupation")
    private String preferredOccupation; // JSON string or comma separated
    
    // Property Features (as JSON or separate table)
    @Column(name = "property_features", columnDefinition = "TEXT")
    private String propertyFeatures; // JSON: ["wifi", "heating", "balcony", etc.]
    
    // Room Features
    @Column(name = "room_features", columnDefinition = "TEXT")
    private String roomFeatures; // JSON: ["furnished", "builtin_wardrobe", "desk", etc.]
    
    // Roommate Info
    @Column(name = "total_housemates")
    private Integer totalHousemates;
    
    @Column(name = "housemates_age_range")
    private String housematesAgeRange;
    
    @Column(name = "housemates_occupation")
    private String housematesOccupation;
    
    @Column(name = "owner_lives_in")
    private Boolean ownerLivesIn = false;
    
    @Column(name = "housemates_description", columnDefinition = "TEXT")
    private String housematesDescription;
    
    // Status
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
    
    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AdvertisementImage> images = new HashSet<>();
    
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
    
    // Getters and Setters
    
}