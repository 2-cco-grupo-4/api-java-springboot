package com.example.picmejava.model.mapper;

import com.example.picmejava.model.*;
import com.example.picmejava.service.evento.dto.CadastroEventoDTO;
import com.example.picmejava.service.evento.dto.PerfilEventoDTO;
import com.example.picmejava.service.evento.dto.RetornoEventoDTO;

public class EventoMapper {

    ClienteMapper clienteMapper = new ClienteMapper();
    FotografoMapper fotografoMapper = new FotografoMapper();

    public RetornoEventoDTO toRetornoEventoDTO(Evento novoEvento) {
        RetornoEventoDTO dto = new RetornoEventoDTO();

        dto.setId(novoEvento.getId());
        dto.setStatusEvento(novoEvento.getStatusEvento());
        dto.setDataRealizacao(novoEvento.getDataRealizacao());
        dto.setValor(novoEvento.getValor());
        dto.setCliente(clienteMapper.toPerfilClienteDTO(novoEvento.getCliente()));
        dto.setFotografo(fotografoMapper.toPerfilFotogradoDTO(novoEvento.getFotografo()));
        dto.setTema(TemaMapper.toPerfilTemaDTO(novoEvento.getTema()));
        if (novoEvento.getEndereco() != null){
            dto.setEndereco(EnderecoMapper.toPerfilEnderecoDTO(novoEvento.getEndereco()));
        }
        return dto;
    }

    public Evento toEvento(Fotografo fotografo, Cliente cliente, Tema tema, CadastroEventoDTO dadosEvento) {
        Evento evento = new Evento();

        evento.setCliente(cliente);
        evento.setFotografo(fotografo);
        evento.setTema(tema);
        evento.setStatusEvento(dadosEvento.getStatusEvento());
        evento.setValor(dadosEvento.getValor());
        evento.setDataRealizacao(dadosEvento.getDataRealizacao());

        return evento;
    }

    public PerfilEventoDTO toPerfilEventoDTO(Evento evento){
        PerfilEventoDTO dto = new PerfilEventoDTO();

        dto.setId(evento.getId());
        dto.setStatusEvento(evento.getStatusEvento());
        dto.setDataRealizacao(evento.getDataRealizacao());
        dto.setValor(evento.getValor());
        dto.setCliente(clienteMapper.toPerfilClienteDTO(evento.getCliente()));
        dto.setFotografo(fotografoMapper.toPerfilFotogradoDTO(evento.getFotografo()));
        dto.setTema(evento.getTema());

        return dto;
    }
}
