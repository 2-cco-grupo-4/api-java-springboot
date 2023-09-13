package com.example.picmejava.controller;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.mapper.FotografoMapper;
import com.example.picmejava.service.usuario.FotografoService;
import com.example.picmejava.service.usuario.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Fotografo Controller",
        description = "Controller responsável pela entidade Fotografo"
)

@RestController
@RequestMapping("/fotografos")
public class FotografoController {

    @Autowired
    private FotografoService serviceFotografo;
    private FotografoMapper fotografoMapper = new FotografoMapper();

    @Operation(summary = "Cadastrar um novo fotógrafo", description = "Passando os dados necessários, podemos cadastrar um novo fotógrafo")
    @PostMapping("/cadastrar")
    public ResponseEntity<PerfilFotografoDTO> cadastrar(@RequestBody @Valid CadastroUsuarioDTO novoCadastroFotografo){
        return ResponseEntity.status(201).body(serviceFotografo.cadastrar(novoCadastroFotografo));
    }

    @Operation(summary = "Listar fotógrafos", description = "Lista todos os fotógrafos cadastrados")
    @SecurityRequirement(name = "Bearer")
    @GetMapping ResponseEntity<List<RetornoFotografoDTO>> listar(){
        if (serviceFotografo.listar().isEmpty()){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(200).body(serviceFotografo.listar());
    }

    @Operation(summary = "Atualizar dados fotógrafo", description = "Passando o ID do fotógrafo e seus novos dados, podemos atualizar suas informações")
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/atualizar/{idFotografo}")
    public ResponseEntity<PerfilFotografoDTO> atualizar(
            @PathVariable Long idFotografo, @RequestBody @Valid AtualizarUsuarioDTO fotografoAtualizado
    ){
        return ResponseEntity.status(200).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.atualizar(idFotografo, fotografoAtualizado)
        ));
    }

    @Operation(summary = "Login fotógrafo", description = "Passando as credenciais válidas de um fotógrafo, é realizado o login na API")
    @PatchMapping("/entrar")
    public ResponseEntity<RetornoFotografoDTO> login(@RequestBody @Valid LoginUsuarioDTO usuarioLoginDTO){
        return ResponseEntity.status(200).body(
                fotografoMapper.toRetornoFotografoDTO(serviceFotografo.login(usuarioLoginDTO))
        );
    }

    @Operation(summary = "Logoff fotógrafo", description = "EndPoint para logoff do fotógrafo, é necessário passar as suas credenciais novamente")
    @SecurityRequirement(name = "Bearer")
    @PatchMapping("/sair")
    public ResponseEntity<PerfilFotografoDTO> logoff(@RequestBody @Valid LoginUsuarioDTO buscarFotografo){
        return ResponseEntity.status(200).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.logoff(buscarFotografo)
        ));
    }


    @Operation(summary = "Atualizar token solicitacao usuário", description = "Endpoint utilizado para atualizar o token de soliticação do usuário no banco de dados")
    @PatchMapping("/atualizarToken/{idFotografo}")
    public ResponseEntity<Fotografo> atualizarTokenSolicitacao(@PathVariable Long idFotografo, @RequestParam String codigo) {

        return ResponseEntity.status(200).body(
                serviceFotografo.atualizarAccessToken(idFotografo, codigo)
        );

    }

    @Operation(summary = "Buscar cliente", description = "Busca um cliente pelo seu nome")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/buscar/{nomeFotografo}")
    public ResponseEntity<List<PerfilFotografoDTO>> buscarFotografo(@PathVariable String nomeFotografo){


        return ResponseEntity.status(200).body(
                serviceFotografo.buscarFotografo(nomeFotografo)
        );

    }


}
