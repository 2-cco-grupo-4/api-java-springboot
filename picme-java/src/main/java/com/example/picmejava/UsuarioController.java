package com.example.picmejava;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private List<Usuario> usuarios;

    public UsuarioController() {
        this.usuarios = new ArrayList<>();
    }

    //METODOS

    //CADASTRAR
    @GetMapping("/cadastrar")
    public Usuario cadastrarUsuario(@RequestBody Usuario novoUsuario){
        usuarios.add(novoUsuario);
        return novoUsuario;
    }

    //ALTERAR E-MAIL
    @PutMapping("/atualizar")
    public Usuario alterarEmail(@RequestBody Usuario novoUsuario){
        for (Usuario usuario : usuarios){
            if (usuario.getEmail().equals(novoUsuario.getEmail()) && usuario.getSenha().equals(novoUsuario.getSenha())){
                usuario.setNome(novoUsuario.getNome());
                return usuario;
            }
        }
        return null;
    }

    //DELETAR CONTA
    @DeleteMapping("/deletar")
    public String deletarConta(@RequestBody Usuario findUsuario){
        for (Usuario usuario : usuarios){
            if (usuario.getEmail().equals(findUsuario.getEmail()) && usuario.getSenha().equals(findUsuario.getSenha())){
                usuarios.remove(usuario);
                return String.format("Conta deletada com sucesso!");
            }
        }
        return String.format("Email ou senha incorreto!");
    }

    //GETTERS AND SETTERS

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
