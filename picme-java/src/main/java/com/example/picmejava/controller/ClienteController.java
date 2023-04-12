package com.example.picmejava.controller;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import com.example.picmejava.model.dto.LoginUsuarioDTO;
import com.example.picmejava.model.dto.PerfilUsuarioDTO;
import com.example.picmejava.model.mapper.ClienteMapper;
import com.example.picmejava.model.mapper.FotografoMapper;
import com.example.picmejava.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService serviceCliente;
    private ClienteMapper clienteMapper = new ClienteMapper();

    @PostMapping()
    public ResponseEntity<PerfilUsuarioDTO> cadastrar(@RequestBody @Valid CadastroUsuarioDTO novoCliente){
        return ResponseEntity.status(201).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.cadastrar(novoCliente)
        ));
    }

    @GetMapping
    public ResponseEntity<List<PerfilUsuarioDTO>> listar(){
        return ResponseEntity.status(200).body(
                serviceCliente.listar().stream().map(cliente -> clienteMapper.toPerfilClienteDTO(cliente)).toList()
        );
    }

    @PutMapping("/atualizar/{idCliente}")
    public ResponseEntity<PerfilUsuarioDTO> atualizar(
            @PathVariable Integer idCliente, @RequestBody @Valid AtualizarUsuarioDTO clienteAtualizado
    ){
        return ResponseEntity.status(200).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.atualizar(idCliente, clienteAtualizado)
        ));
    }

    @PatchMapping("/entrar")
    public ResponseEntity<PerfilUsuarioDTO> login(@RequestBody LoginUsuarioDTO buscarCliente){
        return ResponseEntity.status(200).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.login(buscarCliente)
        ));
    }

    @PatchMapping("/sair")
    public ResponseEntity<PerfilUsuarioDTO> logoff(@RequestBody LoginUsuarioDTO buscarCliente){
        return ResponseEntity.status(200).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.logoff(buscarCliente)
        ));
    }
}
