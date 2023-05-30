package com.example.picmejava.controller;

import com.example.picmejava.model.Evento;
import com.example.picmejava.model.dto.CadastroEventoDTO;
import com.example.picmejava.model.dto.RetornoEventoDTO;
import com.example.picmejava.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Evento Controller",
        description = "Controller responsável pela entidade Evento"
)
@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Operation(summary = "Cadastrar um novo evento", description = "Passando o JSON do evento, podemos cadastrar um novo evento")
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<RetornoEventoDTO> cadastrar(@RequestBody @Valid CadastroEventoDTO novoEvento){
        return ResponseEntity.status(201).body(eventoService.cadastrar(novoEvento));
    }

    @Operation(summary = "Listar eventos", description = "Obter a lista de todos os eventos")
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<RetornoEventoDTO>> listar(){
        if (eventoService.listar().isEmpty()){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(eventoService.listar());
    }
}
