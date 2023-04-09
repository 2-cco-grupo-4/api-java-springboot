package com.example.picmejava.service;

import com.example.picmejava.model.Imagem;
import com.example.picmejava.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagemService {

    @Autowired
    private ImagemRepository imagemRepository;

    public Imagem cadastrar(Integer idAlbum, Imagem novaImagem){
        novaImagem.setIdAlbum(idAlbum);
        return imagemRepository.save(novaImagem);
    }

    public Imagem buscarPorId(Integer idImagem) throws Exception{
        Optional<Imagem> imagemOptional = imagemRepository.findById(idImagem);
        Imagem imagem = imagemOptional.orElseThrow(() -> new Exception("Imagem não encontrada"));
        return imagem;
    }

    public Imagem deletar(int idImagem) throws Exception{
        Imagem imagem = buscarPorId(idImagem);
        imagemRepository.deleteById(idImagem);
        return imagem;
    }

    public List<Imagem> listar(Integer idAlbum){
        return imagemRepository.findAllByIdAlbum(idAlbum);
    }

}
