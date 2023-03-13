package com.example.picmejava.controller;

import com.example.picmejava.dto.UsuarioDTO;
import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Usuario;
import com.example.picmejava.service.ClienteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService serviceCliente;

    public ClienteController() {
        this.serviceCliente = new ClienteService();
    }

    @PostMapping()
    public Cliente cadastrar(@RequestBody Cliente usuario){
        return serviceCliente.cadastrar(usuario);
    }

    @PutMapping("/alterar/senha")
    public UsuarioDTO alterarSenha(@RequestBody Cliente usuario) throws Exception{
        return new UsuarioDTO(serviceCliente.alterarSenha(usuario.getId(), usuario.getSenha()));
    }

    @PatchMapping("/entrar")
    public UsuarioDTO login(@RequestBody Cliente usuario) throws Exception{
        return new UsuarioDTO(serviceCliente.login(usuario));
    }

    @PatchMapping("/sair")
    public String logoff(@RequestBody Cliente usuario) throws Exception{
        return serviceCliente.logoff(usuario);
    }
}
