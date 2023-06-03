package com.example.picmejava.service.builder;

import com.example.picmejava.model.Endereco;
import com.example.picmejava.model.Evento;
import com.example.picmejava.model.dto.CadastroEventoDTO;

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
        cadastroEventoDTO.setIdFotografo(1);

        return cadastroEventoDTO;
    }

    public static Endereco criarEndereco(){
        Endereco endereco = new Endereco();

        endereco.setBairro("Bairro 01");
        endereco.setCep("00000-000");
        endereco.setCidade("Cidade 01");
        endereco.setComplemento("Complemento 01");
        endereco.setEstado("Estado 01");
        endereco.setLogradouro("Logradouro 01");
        endereco.setNumero(1);
        endereco.setEvento(criarEvento());

        return endereco;
    }


}
