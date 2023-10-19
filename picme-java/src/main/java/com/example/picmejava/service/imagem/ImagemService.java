package com.example.picmejava.service.imagem;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Imagem;
import com.example.picmejava.service.imagem.dto.RetornoImagemDTO;
import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.ImagemMapper;
import com.example.picmejava.repository.AlbumRepository;
import com.example.picmejava.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Tag(name = "Imagem Service", description = "APIs relacionadas a operações de imagens")
public class ImagemService {

    private final AlbumRepository albumRepository;
    private final ImagemRepository imagemRepository;
    private final ImagemMapper imagemMapper = new ImagemMapper();

    @Autowired
    public ImagemService(AlbumRepository albumRepository, ImagemRepository imagemRepository) {
        this.albumRepository = albumRepository;
        this.imagemRepository = imagemRepository;
    }

    @Operation(summary = "Cadastrar uma nova imagem em um álbum")
    public RetornoImagemDTO cadastrar(Long idAlbum, Imagem novaImagem) {
        Album album = getAlbum(idAlbum);
        novaImagem.setIdAlbum(album);
        return imagemMapper.toRetornoImagemDTO(imagemRepository.save(novaImagem));
    }

    @Operation(summary = "Buscar uma imagem por ID")
    public Imagem buscarPorId(Long idImagem) {
        return imagemRepository.findById(idImagem)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Imagem não encontrada"));
    }

    @Operation(summary = "Deletar uma imagem por ID")
    public void deletar(Long idImagem) {
        buscarPorId(idImagem);
        imagemRepository.deleteById(idImagem);
    }

    @Operation(summary = "Listar todas as imagens")
    public List<RetornoImagemDTO> listar() {
        List<Imagem> imagens = imagemRepository.findAll();
        return imagens.stream()
                .map(imagem -> imagemMapper.toRetornoImagemDTO(imagem))
                .collect(Collectors.toList());
    }

    private Album getAlbum(Long idAlbum) {
        return albumRepository.findById(idAlbum)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Album não encontrado"));
    }

}
