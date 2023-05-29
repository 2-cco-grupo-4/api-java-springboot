package com.example.picmejava.service.builder;

import com.example.picmejava.model.Evento;
import com.example.picmejava.model.dto.CadastroEventoDTO;

import java.time.LocalDate;
import java.util.List;

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

    public static CadastroEventoDTO criarCadastroEvento(){
        CadastroEventoDTO cadastroEventoDTO = new CadastroEventoDTO();

        cadastroEventoDTO.setAvaliacao(10);
        cadastroEventoDTO.setStatusEvento("ativo");
        cadastroEventoDTO.setValor(30.0);
        cadastroEventoDTO.setDataRealizacao(LocalDate.now());
        cadastroEventoDTO.setIdCliente(1);
        cadastroEventoDTO.setIdTema(1);
        cadastroEventoDTO.setIdEndereco(1);
        cadastroEventoDTO.setIdCliente(1);

        return cadastroEventoDTO;
    }

    public static List<Evento> criarListaEvento(){
        return List.of(
                criarEvento(),
                criarEvento(),
                criarEvento()
        );
    }
}
