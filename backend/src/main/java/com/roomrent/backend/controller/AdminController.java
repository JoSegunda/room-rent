@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired private UserRepository userRepository;
    @Autowired private AnuncioRepository anuncioRepository;

    // 1. Estatísticas para o Dashboard
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalAds", anuncioRepository.count());
        stats.put("totalUsers", userRepository.count());
        stats.put("pendingUsers", userRepository.findAll().stream().filter(u -> !u.isAprovado()).count());
        return ResponseEntity.ok(stats);
    }

    // 2. Listar Utilizadores Pendentes (Aprovação de conta)
    @GetMapping("/users/pendentes")
    public List<User> listarUsersPendentes() {
        return userRepository.findAll().stream()
                .filter(u -> !u.isAprovado())
                .toList();
    }

    // 3. Listar Anúncios Pendentes (Validação de conteúdo)
    @GetMapping("/anuncios/pendentes")
    public List<Anuncio> listarAnunciosPendentes() {
        return anuncioRepository.findAll().stream()
                .filter(a -> !a.isAtivo())
                .toList();
    }

    // 4. Ações de Aprovação
    @PutMapping("/users/{id}/aprovar")
    public ResponseEntity<?> aprovarUser(@PathVariable Long id) {
        User u = userRepository.findById(id).orElseThrow();
        u.setAprovado(true);
        userRepository.save(u);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/anuncios/{id}/ativar")
    public ResponseEntity<?> ativarAnuncio(@PathVariable Long id) {
        Anuncio a = anuncioRepository.findById(id).orElseThrow();
        a.setAtivo(true);
        anuncioRepository.save(a);
        return ResponseEntity.ok().build();
    }
}