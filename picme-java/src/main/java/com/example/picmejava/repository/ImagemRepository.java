package com.example.picmejava.repository;

import com.example.picmejava.model.utils.ListaObj;
import com.example.picmejava.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    ListaObj<Imagem> findAllByIdAlbum(Long idAlbum);

}
