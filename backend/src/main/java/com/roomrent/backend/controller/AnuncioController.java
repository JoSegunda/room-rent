@RestController
@RequestMapping("/api/anuncios")
@CrossOrigin(origins = "*") // Permite pedidos de qualquer origem
public class AnuncioController {

    private final AnuncioRepository anuncioRepository;

    public AnuncioController(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }

    @PostMapping
    public ResponseEntity<Anuncio> criarAnuncio(@RequestBody Anuncio anuncio) {
        Anuncio salvo = anuncioRepository.save(anuncio);
        return ResponseEntity.ok(salvo);
    }
}