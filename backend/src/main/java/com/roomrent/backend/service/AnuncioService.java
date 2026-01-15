package com.roomrent.backend.service;

import com.roomrent.backend.dto.AnuncioRequestDTO;
import com.roomrent.backend.model.Anuncio;
import com.roomrent.backend.repository.AnuncioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnuncioService {

    private final AnuncioRepository repository;

    private static final String UPLOAD_DIR = "uploads/";

    public AnuncioService(AnuncioRepository repository) {
        this.repository = repository;
        new File(UPLOAD_DIR).mkdirs();
    }

    public Anuncio criarAnuncio(AnuncioRequestDTO dto, MultipartFile[] fotos) throws Exception {

        Anuncio anuncio = new Anuncio();

        anuncio.setTipoAnuncio(dto.getTipoAnuncio());
        anuncio.setUserId(dto.getUserId());
        anuncio.setCidade(dto.getCidade());
        anuncio.setEnderecoCompleto(dto.getEnderecoCompleto());
        anuncio.setCodigoPostal(dto.getCodigoPostal());
        anuncio.setPreco(dto.getPreco());
        anuncio.setQuartosDisponiveis(dto.getQuartosDisponiveis());
        anuncio.setIdadeMinima(dto.getIdadeMinima());
        anuncio.setIdadeMaxima(dto.getIdadeMaxima());
        anuncio.setGenero(dto.getGenero());
        anuncio.setAreaImovel(dto.getAreaImovel());
        anuncio.setTipologia(dto.getTipologia());
        anuncio.setAndar(dto.getAndar());
        anuncio.setTitulo(dto.getTitulo());
        anuncio.setDescricao(dto.getDescricao());

        List<String> caminhosFotos = new ArrayList<>();

        if (fotos != null && fotos.length > 0) {
            if (fotos.length > 3) {
                throw new RuntimeException("Máximo de 3 fotos permitido");
            }

            for (MultipartFile foto : fotos) {
                String nomeArquivo = System.currentTimeMillis() + "_" + foto.getOriginalFilename();
                File destino = new File(UPLOAD_DIR + nomeArquivo);
                foto.transferTo(destino);
                caminhosFotos.add(destino.getAbsolutePath());
            }
        }

        anuncio.setFotos(caminhosFotos);

        return repository.save(anuncio);
    }
}
