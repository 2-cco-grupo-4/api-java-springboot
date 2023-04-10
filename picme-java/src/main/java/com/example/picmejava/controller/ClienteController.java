package com.example.picmejava.controller;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.dto.PerfilClienteDTO;
import com.example.picmejava.model.mapper.ClienteMapper;
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
    private ClienteMapper clienteMapper = new ClienteMapper();

    @PostMapping()
    public ResponseEntity<PerfilClienteDTO> cadastrar(@RequestBody @Valid Cliente novoCliente){
        return ResponseEntity.status(201).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.cadastrar(novoCliente)
        ));
    }

    @PutMapping("/alterar/senha")
    public ResponseEntity<PerfilClienteDTO> alterarSenha(@RequestBody @Valid Cliente clienteAtualizado) throws Exception{
        return ResponseEntity.status(200).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.alterarSenha(clienteAtualizado.getId(), clienteAtualizado.getSenha())
        ));
    }

    @PatchMapping("/entrar")
    public ResponseEntity<PerfilClienteDTO> login(@RequestBody @Valid Cliente buscarCliente) throws Exception{
        return ResponseEntity.status(200).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.login(buscarCliente)
        ));
    }

    @PatchMapping("/sair")
    public ResponseEntity<PerfilClienteDTO> logoff(@RequestBody @Valid Cliente buscarCliente) throws Exception{
        return ResponseEntity.status(200).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.logoff(buscarCliente)
        ));
    }
}
