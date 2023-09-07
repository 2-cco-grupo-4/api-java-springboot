package com.example.picmejava.repository;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FotografoRepository extends JpaRepository<Fotografo, Long> {
    Optional<Fotografo> findByEmail(String email);

    Optional<Fotografo> findByEmailAndSenha(String email, String senha);

    Optional<Fotografo> findByCpf(String cpf);
}
