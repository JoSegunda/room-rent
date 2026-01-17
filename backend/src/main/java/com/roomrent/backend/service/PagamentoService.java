package com.roomrent.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PagamentoService {
    private final RestTemplate restTemplate;

    public PagamentoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String obterReferenciaMB(Double valor) {
        String url = "https://magno.di.uevora.pt/tweb/t2/mbref4payment?amount=" + valor;
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return "Entidade: 12345 | Ref: 000 000 000 | Valor: " + valor;
        }
    }
}