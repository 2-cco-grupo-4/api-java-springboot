package com.example.picmejava.service.usuario;

import com.example.picmejava.model.Usuario;
import com.example.picmejava.repository.UsuarioRepository;
import com.example.picmejava.service.usuario.dto.ValidarNovoUsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Tag(name = "Usuário Service", description = "Service de operações com os usuários da plataforma")
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Listar usuários com email ou cpf informado", description = "Retorna quais cpfs e emails já estão cadastrados em nossa base de acordo com o que foi informado")
    public boolean validarEmailCpf(String email, String cpf) {
        boolean hasUser = false;
        List <Usuario> listaUsers = usuarioRepository.findByEmailOrCpf(email, cpf).get();
        if(listaUsers.size() > 0){
            hasUser = true;
        }
        return hasUser;
    }

}
