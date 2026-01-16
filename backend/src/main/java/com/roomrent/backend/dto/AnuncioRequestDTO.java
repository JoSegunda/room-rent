package com.roomrent.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnuncioRequestDTO {

    private String tipoAnuncio;
    private Long userId;

    private String cidade;
    private String enderecoCompleto;
    private String codigoPostal;

    private Double preco;
    private Integer quartosDisponiveis;

    private Integer idadeMin;
    private Integer idadeMax;
    private String generoProcurado;

    private Double areaImovel;
    private String tipologia;
    private Integer andar;

    private String titulo;
}
