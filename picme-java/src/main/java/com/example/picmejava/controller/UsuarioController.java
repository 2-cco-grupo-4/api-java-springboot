package com.example.picmejava.controller;

import com.example.picmejava.repository.UsuarioRepository;
import com.example.picmejava.service.usuario.UsuarioService;
import com.example.picmejava.service.usuario.dto.ValidarNovoUsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/usuario")
@Tag(
        name = "Usuário Controller",
        description = "Controller responsável pela entidade Usuário"
)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/validarNovoUsuario")
    @Operation(summary = "Validar novo usuário", description = "Caso já exista um usuário com o cpf ou email informado será retornado o item repetido")
    public ResponseEntity validarNovoUsuario(@RequestBody @Valid ValidarNovoUsuarioDTO dadosUnicos) {
        List<ValidarNovoUsuarioDTO> listaValidacao = new ArrayList<>();
        boolean hasUsuario = usuarioService.validarEmailCpf(dadosUnicos.getEmail(), dadosUnicos.getCpf());
        if(!hasUsuario){
            return ResponseEntity.status(201).body("Usuário válido!");
        }else{
            return ResponseEntity.status(204).body("Usuário já cadastrado!");
        }
    }

}
