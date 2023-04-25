package com.example.picmejava.controller;

import com.example.picmejava.model.dto.*;
import com.example.picmejava.model.mapper.ClienteMapper;
import com.example.picmejava.service.ClienteService;
import com.example.picmejava.service.autenticacao.dto.UsuarioLoginDTO;
import com.example.picmejava.service.autenticacao.dto.UsuarioTokenDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService serviceCliente;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private ClienteMapper clienteMapper = new ClienteMapper();

    @PostMapping("/cadastrar")
    public ResponseEntity<PerfilClienteDTO> cadastrar(@RequestBody @Valid CadastroUsuarioDTO novoCadastroCliente){
        String senhaCriptografada = passwordEncoder.encode(novoCadastroCliente.getSenha());
        novoCadastroCliente.setSenha(senhaCriptografada);

        return ResponseEntity.status(201).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.cadastrar(novoCadastroCliente)
        ));
    }

    @GetMapping
    public ResponseEntity<List<PerfilClienteDTO>> listar(){
        return ResponseEntity.status(200).body(
                serviceCliente.listar()
                        .stream()
                        .filter(Objects::nonNull)
                        .map(cliente -> clienteMapper.toPerfilClienteDTO(cliente))
                        .toList()
        );
    }
    @PutMapping("/atualizar/{idCliente}")
    public ResponseEntity<PerfilClienteDTO> atualizar(
            @PathVariable Integer idCliente, @RequestBody @Valid AtualizarUsuarioDTO clienteAtualizado
    ){
        return ResponseEntity.status(200).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.atualizar(idCliente, clienteAtualizado)
        ));
    }

    @PatchMapping("/entrar")
    public ResponseEntity<UsuarioTokenDTO> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO){
        return ResponseEntity.status(200).body(serviceCliente.autenticar(usuarioLoginDTO));
    }

    @PatchMapping("/sair")
    public ResponseEntity<PerfilClienteDTO> logoff(@RequestBody LoginUsuarioDTO buscarCliente){
        return ResponseEntity.status(200).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.logoff(buscarCliente)
        ));
    }
}
