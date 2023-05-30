package com.example.picmejava.controller;

import com.example.picmejava.model.Evento;
import com.example.picmejava.model.dto.CadastroEventoDTO;
import com.example.picmejava.model.dto.RetornoEventoDTO;
import com.example.picmejava.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<RetornoEventoDTO> cadastrar(@RequestBody @Valid CadastroEventoDTO novoEvento){
        return ResponseEntity.status(201).body(eventoService.cadastrar(novoEvento));
    }

    @GetMapping
    public ResponseEntity<List<RetornoEventoDTO>> listar(){
        if (eventoService.listar().isEmpty()){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(eventoService.listar());
    }
}
