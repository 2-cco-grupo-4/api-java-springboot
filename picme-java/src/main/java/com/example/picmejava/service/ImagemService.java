package com.example.picmejava.service;

import com.example.picmejava.lista.Lista;
import com.example.picmejava.model.Album;
import com.example.picmejava.model.Imagem;
import com.example.picmejava.model.dto.RetornoImagemDTO;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.ImagemMapper;
import com.example.picmejava.repository.AlbumRepository;
import com.example.picmejava.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@Service
@Tag(name = "Imagem Service", description = "APIs relacionadas a operações de imagens")
public class ImagemService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    private ImagemMapper imagemMapper = new ImagemMapper();

    @Operation(summary = "Cadastrar uma nova imagem em um álbum")
    public RetornoImagemDTO cadastrar(Integer idAlbum, Imagem novaImagem){
        Optional<Album> albumOptional = albumRepository.findById(idAlbum);
        albumOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Album não encontrado"));

        novaImagem.setIdAlbum(albumOptional.get());
        return imagemMapper.toRetornoImagemDTO(imagemRepository.save(novaImagem));
    }

    @Operation(summary = "Buscar uma imagem por ID")
    public Imagem buscarPorId(Integer idImagem){
        Optional<Imagem> imagemOptional = imagemRepository.findById(idImagem);
        Imagem imagem = imagemOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Imagem não encontrada"));
        return imagem;
    }

    @Operation(summary = "Deletar uma imagem por ID")
    public void deletar(int idImagem){
        buscarPorId(idImagem);
        imagemRepository.deleteById(idImagem);
    }

    @Operation(summary = "Listar todas as imagens")
    public List<RetornoImagemDTO> listar(){
        List<Imagem> imagems = imagemRepository.findAll();
        List<RetornoImagemDTO> imagemDTO = imagems.stream()
                .map(imagem -> imagemMapper.toRetornoImagemDTO(imagem))
                .toList();

        return imagemDTO;
    }

}
