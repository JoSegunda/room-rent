package com.roomrent.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    private boolean aprovado = false; // Utilizador começa bloqueado
    private String role = "USER";    // "USER" ou "ADMIN"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Anuncio> anuncios;

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
