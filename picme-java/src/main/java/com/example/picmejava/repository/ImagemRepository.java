package com.example.picmejava.repository;

import com.example.picmejava.lista.Lista;
import com.example.picmejava.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
    Lista<Imagem> findAllByIdAlbum(Integer idAlbum);

    List<Imagem> findByIdAlbum(Integer idAlbum);


}
