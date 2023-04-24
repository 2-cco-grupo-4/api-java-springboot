package com.example.picmejava.controller;

import com.example.picmejava.model.dto.*;
import com.example.picmejava.model.mapper.ClienteMapper;
import com.example.picmejava.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Cliente Controller",
        description = "Controller responsável pela entidade Cliente"
)

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService serviceCliente;
    private ClienteMapper clienteMapper = new ClienteMapper();

    @Operation(summary = "Cadastrar um novo cliente", description = "Passando os dados necessários, podemos cadastrar um novo cliente")
    @PostMapping()
    public ResponseEntity<PerfilClienteDTO> cadastrar(@RequestBody @Valid CadastroUsuarioDTO novoCliente){
        return ResponseEntity.status(201).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.cadastrar(novoCliente)
        ));
    }

    @Operation(summary = "Listar clientes", description = "Lista todos os clientes cadastrados")
    @GetMapping
    public ResponseEntity<List<PerfilClienteDTO>> listar(){
        return ResponseEntity.status(200).body(
                serviceCliente.listar().stream().map(cliente -> clienteMapper.toPerfilClienteDTO(cliente)).toList()
        );
    }

    @Operation(summary = "Atualizar dados cliente", description = "Passando o ID do cliente e seus novos dados, podemos atualizar suas informações")
    @PutMapping("/atualizar/{idCliente}")
    public ResponseEntity<PerfilClienteDTO> atualizar(
            @PathVariable Integer idCliente, @RequestBody @Valid AtualizarUsuarioDTO clienteAtualizado
    ){
        return ResponseEntity.status(200).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.atualizar(idCliente, clienteAtualizado)
        ));
    }

    @Operation(summary = "Login cliente", description = "Passando as credenciais válidas de um cliente, é realizado o login na API")
    @PatchMapping("/entrar")
    public ResponseEntity<PerfilClienteDTO> login(@RequestBody LoginUsuarioDTO buscarCliente){
        return ResponseEntity.status(200).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.login(buscarCliente)
        ));
    }

    @Operation(summary = "Logoff cliente", description = "EndPoint para logoff do cliente, é necessário passar as suas credenciais novamente")
    @PatchMapping("/sair")
    public ResponseEntity<PerfilClienteDTO> logoff(@RequestBody LoginUsuarioDTO buscarCliente){
        return ResponseEntity.status(200).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.logoff(buscarCliente)
        ));
    }
}
