package com.example.picmejava.service.builder;

import com.example.picmejava.model.Evento;

import java.time.LocalDate;

public class EventoBuilder {

    public EventoBuilder() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static Evento criarEvento(){
        Evento evento = new Evento();

        evento.setTema(TemaBuilder.criarTema());
        evento.setFotografo(FotografoBuilder.criarFotografo());
        evento.setStatusEvento("ativo");
        evento.setId(1);
        evento.setCliente(ClienteBuilder.criarCliente());
        evento.setAvaliacao(10);
        evento.setValor(2000.0);
        evento.setDataRealizacao(LocalDate.now());

        return evento;
    }
}
