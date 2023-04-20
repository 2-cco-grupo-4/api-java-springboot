package com.example.picmejava.repository;

import com.example.picmejava.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    Lista<Album> findAllByIdFotografo(Integer idFotografo);
}
