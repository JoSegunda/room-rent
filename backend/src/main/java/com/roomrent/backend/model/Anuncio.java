package com.roomrent.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "anuncios")
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // PROCURA ou OFERTA

    private String cidade;
    private String endereco;
    private String codigoPostal;

    private Double preco;
    private Integer quartosDisponiveis;

    private Integer idadeMin;
    private Integer idadeMax;
    private String genero;

    private Double area;
    private String tipologia;
    private Integer andar;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // getters e setters
    public Long getId() { return id; }
    public String getTipo() { return tipo; }
    public String getTitulo() { return titulo; }
    public User getUser() { return user; }

    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setUser(User user) { this.user = user; }
}
