package com.example.picmejava.controller;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import com.example.picmejava.model.dto.PerfilUsuarioDTO;
import com.example.picmejava.model.mapper.UsuarioMapper;
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
    private UsuarioMapper usuarioMapper = new UsuarioMapper();

    @PostMapping()
    public ResponseEntity<PerfilUsuarioDTO> cadastrar(@RequestBody @Valid CadastroUsuarioDTO novoCliente){
        return ResponseEntity.status(201).body(usuarioMapper.toPerfilClienteDTO(
                serviceCliente.cadastrar(novoCliente)
        ));
    }

    @GetMapping
    public ResponseEntity<List<PerfilUsuarioDTO>> listar(){
        return ResponseEntity.status(200).body(
                serviceCliente.listar().stream().map(cliente -> usuarioMapper.toPerfilClienteDTO(cliente)).toList()
        );
    }

    @PutMapping("/atualizar/{idCliente}")
    public ResponseEntity<PerfilUsuarioDTO> atualizar(
            @PathVariable Integer idCliente, @RequestBody @Valid AtualizarUsuarioDTO clienteAtualizado
    ){
        return ResponseEntity.status(200).body(usuarioMapper.toPerfilClienteDTO(
                serviceCliente.atualizar(idCliente, clienteAtualizado)
        ));
    }

    @PatchMapping("/entrar")
    public ResponseEntity<PerfilUsuarioDTO> login(@RequestBody Cliente buscarCliente){
        return ResponseEntity.status(200).body(usuarioMapper.toPerfilClienteDTO(
                serviceCliente.login(buscarCliente)
        ));
    }

    @PatchMapping("/sair")
    public ResponseEntity<PerfilUsuarioDTO> logoff(@RequestBody Cliente buscarCliente){
        return ResponseEntity.status(200).body(usuarioMapper.toPerfilClienteDTO(
                serviceCliente.logoff(buscarCliente)
        ));
    }
}
