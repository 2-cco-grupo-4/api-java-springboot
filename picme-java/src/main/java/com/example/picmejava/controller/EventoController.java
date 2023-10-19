package com.example.picmejava.controller;

import com.example.picmejava.service.evento.dto.CadastroSessaoDTO;
import com.example.picmejava.service.evento.dto.RetornoEventoDTO;
import com.example.picmejava.service.evento.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Sessao Controller",
        description = "Controller responsável pela entidade Sessao"
)
@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private SessaoService sessaoService;

    @Operation(summary = "Cadastrar um novo evento", description = "Passando o JSON do evento, podemos cadastrar um novo evento")
    @PostMapping
    public ResponseEntity<RetornoEventoDTO> cadastrar(@RequestBody @Valid CadastroSessaoDTO novoEvento){
        return ResponseEntity.status(201).body(sessaoService.cadastrar(novoEvento));
    }

    @Operation(summary = "Listar eventos", description = "Obter a lista de todos os eventos")
    @GetMapping
    public ResponseEntity<List<RetornoEventoDTO>> listar(){
        if (sessaoService.listar().isEmpty()){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(sessaoService.listar());
    }
}
