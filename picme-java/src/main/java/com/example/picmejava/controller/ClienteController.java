package com.example.picmejava.controller;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.service.usuario.ClienteService;
import com.example.picmejava.service.usuario.dto.AtualizarUsuarioDTO;
import com.example.picmejava.service.usuario.dto.CadastroUsuarioDTO;
import com.example.picmejava.service.usuario.dto.LoginUsuarioDTO;
import com.example.picmejava.service.usuario.dto.PerfilClienteDTO;
import com.example.picmejava.model.mapper.ClienteMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @Operation(summary = "Cadastrar um novo cliente",
            description = "Passando os dados necessários, podemos cadastrar um novo cliente"
    )

    @PostMapping("/cadastrar")
    public ResponseEntity<PerfilClienteDTO> cadastrar(@RequestBody @Valid CadastroUsuarioDTO novoCadastroCliente){
        return ResponseEntity.status(201).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.cadastrar(novoCadastroCliente)
        ));
    }

    @Operation(summary = "Listar clientes", description = "Lista todos os clientes cadastrados")
    @SecurityRequirement(name = "Bearer")

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

    @Operation(summary = "Atualizar dados cliente", description = "Passando o ID do cliente e seus novos dados, podemos atualizar suas informações")
    @SecurityRequirement(name = "Bearer")

    @PutMapping("/atualizar/{idCliente}")
    public ResponseEntity<PerfilClienteDTO> atualizar(
            @PathVariable Long idCliente, @RequestBody @Valid AtualizarUsuarioDTO clienteAtualizado
    ){
        return ResponseEntity.status(200).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.atualizar(idCliente, clienteAtualizado)
        ));
    }

    @Operation(summary = "Login/Logoff cliente", description = "Passando as credenciais válidas de um cliente, é realizado o login na API. Passando as mesmas credenciais novamente, é feito o logoff.")
    @SecurityRequirement(name = "Bearer")
    @PatchMapping("/autenticar")
    public ResponseEntity<PerfilClienteDTO> autenticar(@RequestBody LoginUsuarioDTO usuarioLoginDTO){
        Cliente cliente = serviceCliente.validarCliente(usuarioLoginDTO.getEmail(), usuarioLoginDTO.getSenha());
        boolean autenticar = !cliente.isAutenticado();

        return ResponseEntity.status(200).body(clienteMapper.toPerfilClienteDTO(
                serviceCliente.autenticarCliente(usuarioLoginDTO, autenticar)
        ));
    }
}
