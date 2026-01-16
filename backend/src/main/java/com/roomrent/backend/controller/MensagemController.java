package com.roomrent.backend.controller;

@RestController
@RequestMapping("/api/mensagens")
@CrossOrigin(origins = "*")
public class MensagemController {
    
    @Autowired
    private MensagemRepository mensagemRepository;

    @PostMapping
    public ResponseEntity<Mensagem> enviarMensagem(@RequestBody Mensagem mensagem) {
        return ResponseEntity.ok(mensagemRepository.save(mensagem));
    }

    @GetMapping("/anuncio/{anuncioId}")
    public List<Mensagem> verMensagensDoAnuncio(@PathVariable Long anuncioId) {
        return mensagemRepository.findByAnuncioId(anuncioId);
    }
}