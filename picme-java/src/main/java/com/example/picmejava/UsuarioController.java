package com.example.picmejava;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        if (novoUsuario.isMaiordeIdade(novoUsuario.getDataNasc()).equals(true)){
            novoUsuario.setAutenticado(false);
            usuarios.add(novoUsuario);
            return novoUsuario;
        }
        return null;
    }

    //ALTERAR E-MAIL
    @PutMapping("/atualizar")
    public Usuario alterarEmail(@RequestBody Usuario novoUsuario){
        for (Usuario usuario : usuarios){
            if (usuario.getEmail().equals(novoUsuario.getEmail()) && usuario.getSenha().equals(novoUsuario.getSenha())){
                usuario.setEmail(novoUsuario.getEmail());
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

    //LOGIN
    @PatchMapping("/entrar")
    public String login(Usuario findUsuario) {
        for (Usuario usuario : usuarios){
            if (usuario.getEmail().equals(findUsuario.getEmail()) && usuario.getSenha().equals(findUsuario.getSenha())){
                usuario.setAutenticado(true);
                return String.format("Usuario %s autenticado!", usuario.getNome());
            }
        }
        return "Usuário não encontrado!";
    }

    //LOGOFF
    @PatchMapping("/sair")
    public String logoff(Usuario findUsuario) {
        for (Usuario usuario : usuarios){
            if (usuario.getEmail().equals(findUsuario.getEmail()) && usuario.getSenha().equals(findUsuario.getSenha())){
                if (usuario.getAutenticado().equals(false)){
                    return String.format("Usuário %s não está ativo", usuario.getNome());
                }else {
                    usuario.setAutenticado(false);
                    return String.format("Usuario %s fez logoff com sucesso!", usuario.getNome());
                }
            }
        }

        return "Usuario não encontrado!";
    }
}
