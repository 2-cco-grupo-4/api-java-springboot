package com.example.picmejava.controller;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService serviceCliente;

    @PostMapping()
    public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid Cliente novoCliente){
        return ResponseEntity.status(201).body(serviceCliente.cadastrar(novoCliente));
    }

    @PutMapping("/alterar/senha")
    public ResponseEntity<Cliente> alterarSenha(@RequestBody @Valid Cliente clienteAtualizado) throws Exception{
        return ResponseEntity.status(200).body(serviceCliente.alterarSenha(clienteAtualizado.getId(), clienteAtualizado.getSenha()));
    }

    @PatchMapping("/entrar")
    public ResponseEntity<Cliente> login(@RequestBody Cliente buscarCliente) throws Exception{
        return ResponseEntity.status(200).body(serviceCliente.login(buscarCliente));
    }

    @PatchMapping("/sair")
    public ResponseEntity<Cliente> logoff(@RequestBody Cliente buscarCliente) throws Exception{
        return ResponseEntity.status(200).body(serviceCliente.logoff(buscarCliente));
    }
}
