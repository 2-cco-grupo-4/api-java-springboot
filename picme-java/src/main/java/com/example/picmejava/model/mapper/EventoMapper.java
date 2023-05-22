package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Evento;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.dto.PerfilEnderecoDTO;
import com.example.picmejava.model.dto.PerfilEventoDTO;
import com.example.picmejava.model.dto.RetornoEventoDTO;

public class EventoMapper {

    ClienteMapper clienteMapper = new ClienteMapper();
    FotografoMapper fotografoMapper = new FotografoMapper();

    public RetornoEventoDTO toRetornoEventoDTO(Evento novoEvento) {
        RetornoEventoDTO dto = new RetornoEventoDTO();

        dto.setId(novoEvento.getId());
        dto.setAvaliacao(novoEvento.getAvaliacao());
        dto.setStatusEvento(novoEvento.getStatusEvento());
        dto.setDataRealizacao(novoEvento.getDataRealizacao());
        dto.setValor(novoEvento.getValor());
        dto.setCliente(clienteMapper.toPerfilClienteDTO(novoEvento.getCliente()));
        dto.setFotografo(fotografoMapper.toPerfilFotogradoDTO(novoEvento.getFotografo()));
        dto.setTema(novoEvento.getTema());
        if (novoEvento.getEndereco() != null){
            dto.setEndereco(EnderecoMapper.toPerfilEnderecoDTO(novoEvento.getEndereco()));

        }
        return dto;
    }

    public Evento toEvento(Fotografo fotografo, Cliente cliente, Tema tema, Evento novoEvento) {

        novoEvento.setCliente(cliente);
        novoEvento.setFotografo(fotografo);
        novoEvento.setTema(tema);

        return novoEvento;
    }

    public PerfilEventoDTO toPerfilEventoDTO(Evento evento){
        PerfilEventoDTO dto = new PerfilEventoDTO();

        dto.setId(evento.getId());
        dto.setAvaliacao(evento.getAvaliacao());
        dto.setStatusEvento(evento.getStatusEvento());
        dto.setDataRealizacao(evento.getDataRealizacao());
        dto.setValor(evento.getValor());
        dto.setCliente(clienteMapper.toPerfilClienteDTO(evento.getCliente()));
        dto.setFotografo(fotografoMapper.toPerfilFotogradoDTO(evento.getFotografo()));
        dto.setTema(evento.getTema());

        return dto;
    }

}
