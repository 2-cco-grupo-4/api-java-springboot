package com.example.picmejava.repository;

import com.example.picmejava.model.Usuario;
import com.example.picmejava.service.usuario.dto.ValidarNovoUsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByEmail(String email);

    Optional<Usuario> findByEmailAndSenha(String email, String senha);

    Optional <List<Usuario>> findByEmailOrCpf(String email, String cpf);

}
