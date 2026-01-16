package com.roomrent.backend.service;


@Service
public class PagamentoService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String obterReferenciaMB(Double valor) {
        String url = "https://magno.di.uevora.pt/tweb/t2/mbref4payment?amount=" + valor;
        try {
            // Este serviço retorna os dados de pagamento (Entidade/Referência)
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return "Erro ao gerar referência MB";
        }
    }
}