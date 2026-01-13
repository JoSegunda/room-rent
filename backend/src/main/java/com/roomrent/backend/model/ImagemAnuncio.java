import com.roomrent.backend.model;

@Entity
@Table(name = "ImagensAnuncio")
public class ImagemAnuncio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    
    @Column(name = "is_main")
    private Boolean isMain = false;
    
    @Column(name = "display_order")
    private Integer displayOrder = 0;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anuncio", nullable = false)
    private Advertisement anuncio;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public Boolean getIsMain() { return isMain; }
    public void setIsMain(Boolean main) { isMain = main; }
    
    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
    
    public Advertisement getAnuncio() { return advertisement; }
    public void setAnuncio(Anuncio anuncio) { this.anuncio = anuncio; }
}
