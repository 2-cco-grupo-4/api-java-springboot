package com.example.picmejava.repository;

import com.example.picmejava.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}