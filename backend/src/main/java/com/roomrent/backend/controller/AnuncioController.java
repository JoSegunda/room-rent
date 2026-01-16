package com.roomrent.backend.controller;

import com.roomrent.backend.model.Anuncio;
import com.roomrent.backend.repository.AnuncioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/anuncios")
@CrossOrigin(origins = "*")
public class AnuncioController {

    private final AnuncioRepository anuncioRepository;

    public AnuncioController(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }

    @GetMapping("/paginado")
    public Page<Anuncio> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "recentes") String sort) {

        Sort sorting = switch (sort) {
            case "baratos" -> Sort.by("preco").ascending();
            case "caro" -> Sort.by("preco").descending();
            case "tamanho" -> Sort.by("area").descending();
            default -> Sort.by("id").descending();
        };

        Pageable pageable = PageRequest.of(page, size, sorting);
        return anuncioRepository.findFiltered(tipo, search, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anuncio> obterPorId(@PathVariable Long id) {
        return anuncioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<?> criarAnuncio(@RequestBody Anuncio anuncio, @RequestParam Long userId) {
        // 1. Atribuir o utilizador ao anúncio
        User user = userRepository.findById(userId).orElseThrow();
        anuncio.setUser(user);

        // 2. Salvar o anúncio (O ID único é gerado automaticamente pelo @GeneratedValue)
        Anuncio salvo = anuncioRepository.save(anuncio);

        // 3. Obter referência de pagamento para o valor do anúncio (ex: taxa fixa de 5.0)
        String dadosPagamento = pagamentoService.obterReferenciaMB(5.0);

        // Retornamos um objeto com o anúncio e os dados de pagamento
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("anuncio", salvo);
        resposta.put("pagamento", dadosPagamento);

        return ResponseEntity.ok(resposta);
    }
}