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

import java.util.List;
import java.util.Optional;

@Service
public class ImagemService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    ImagemMapper imagemMapper = new ImagemMapper();

    public RetornoImagemDTO cadastrar(Integer idAlbum, Imagem novaImagem){
        Optional<Album> albumOptional = albumRepository.findById(idAlbum);
        albumOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Album não encontrado"));

        novaImagem.setIdAlbum(albumOptional.get());
        return imagemMapper.toRetornoImagemDTO(imagemRepository.save(novaImagem));
    }

    public Imagem buscarPorId(Integer idImagem){
        Optional<Imagem> imagemOptional = imagemRepository.findById(idImagem);
        Imagem imagem = imagemOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Imagem não encontrada"));
        return imagem;
    }

    public void deletar(int idImagem){
        buscarPorId(idImagem);
        imagemRepository.deleteById(idImagem);
    }

    public List<RetornoImagemDTO> listar(){
        List<Imagem> imagems = imagemRepository.findAll();

        return imagems.stream()
                .map(imagem -> imagemMapper.toRetornoImagemDTO(imagem))
                .toList();
    }

    public List<RetornoImagemDTO> listarPorAlbum(Integer idAlbum){
        List<Imagem> imagems = imagemRepository.findByIdAlbum(idAlbum);

        return imagems.stream()
                .map(imagem -> imagemMapper.toRetornoImagemDTO(imagem))
                .toList();
    }

}
