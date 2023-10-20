package com.example.picmejava.repository;

import com.example.picmejava.model.Avaliacao;
import com.example.picmejava.model.Fotografo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

}
