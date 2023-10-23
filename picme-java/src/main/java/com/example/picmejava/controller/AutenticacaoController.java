package com.example.picmejava.controller;

import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.infra.security.DadosTokenJWT;
import com.example.picmejava.infra.security.TokenService;
import com.example.picmejava.model.Usuario;
import com.example.picmejava.repository.UsuarioRepository;
import com.example.picmejava.service.usuario.dto.LoginUsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

@RestController

@RequestMapping("/login")
@Tag(name = "Autenticação", description = "Endpoints para autenticação")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(summary = "Efetuar login", description = "Realiza o processo de autenticação do usuário e retorna o token de acesso.")
    public ResponseEntity efetuarLogin(@RequestBody @Valid LoginUsuarioDTO login) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());
        var authentication = manager.authenticate(authenticationToken);

        Usuario principal = (Usuario) authentication.getPrincipal();

        var tokenJWT = tokenService.gerarToken(principal);

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT,
                principal.getId(), principal.getNome(), principal.getTipoUsuario(), principal.getTokenSolicitacao()));
    }
}
