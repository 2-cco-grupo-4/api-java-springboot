package com.example.picmejava.controller;

import com.example.picmejava.model.Cliente;
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
    public Cliente cadastrar(@RequestBody Cliente novoCliente){
        return serviceCliente.cadastrar(novoCliente);
    }

//    @GetMapping("/{idCliente}")
//    public UsuarioDTO buscarCLientePorId(@PathVariable Integer idCliente) throws Exception{
//        return new UsuarioDTO(serviceCliente.buscarClientePorId(idCliente));
//    }
//
//    @PutMapping("/alterar/senha")
//    public UsuarioDTO alterarSenha(@RequestBody Cliente buscarCliente) throws Exception{
//        return new UsuarioDTO(serviceCliente.alterarSenha(buscarCliente.getId(), buscarCliente.getSenha()));
//    }
//
//    @PatchMapping("/entrar")
//    public UsuarioDTO login(@RequestBody Cliente buscarCliente) throws Exception{
//        return new UsuarioDTO(serviceCliente.login(buscarCliente));
//    }
//
//    @PatchMapping("/sair")
//    public String logoff(@RequestBody Cliente buscarCliente) throws Exception{
//        return serviceCliente.logoff(buscarCliente);
//    }
}
