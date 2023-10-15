package com.example.picmejava.repository;

import com.example.picmejava.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Sessao, Long> {
}
