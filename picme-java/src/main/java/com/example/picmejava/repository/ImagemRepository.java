package com.example.picmejava.repository;

import com.example.picmejava.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
    List<Imagem> findAllByIdAlbum(Integer idAlbum);
}
