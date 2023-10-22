package com.example.picmejava.repository;

import com.example.picmejava.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    List<Sessao> findAllByFotografoId(Long id);

}
