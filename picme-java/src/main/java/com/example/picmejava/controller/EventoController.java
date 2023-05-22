package com.example.picmejava.controller;

import com.example.picmejava.model.Evento;
import com.example.picmejava.repository.EventoRepository;
import com.example.picmejava.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

//    @PostMapping
//    public ResponseEntity<Evento> cadastrar(@RequestBody Evento novoEvento){
//        return ResponseEntity.status(201).body(eventoService.cadastrar(novoEvento));
//    }
}
