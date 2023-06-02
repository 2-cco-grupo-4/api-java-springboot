package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Endereco;
import com.example.picmejava.model.Evento;
import com.example.picmejava.model.dto.CadastroEnderecoDTO;
import com.example.picmejava.model.dto.PerfilEnderecoDTO;
import com.example.picmejava.model.dto.RetornoEnderecoDTO;

public class EnderecoMapper {

    EventoMapper eventoMapper = new EventoMapper();

    public static PerfilEnderecoDTO toPerfilEnderecoDTO(Endereco endereco) {
        PerfilEnderecoDTO dto = new PerfilEnderecoDTO();

        dto.setId(endereco.getId());
        dto.setCep(endereco.getCep());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getEstado());
        dto.setBairro(endereco.getBairro());
        dto.setRua(endereco.getLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());

        return dto;
    }

    public static Endereco toEndereco(CadastroEnderecoDTO novoEndereco, Evento evento) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(novoEndereco.getLogradouro());
        endereco.setCidade(novoEndereco.getCidade());
        endereco.setCep(novoEndereco.getCep());
        endereco.setEstado(novoEndereco.getEstado());
        endereco.setComplemento(novoEndereco.getComplemento());
        endereco.setEvento(evento);
        endereco.setNumero(novoEndereco.getNumero());

        return endereco;
    }

    public RetornoEnderecoDTO toRetornoEnderecoDTO(Endereco endereco){
        RetornoEnderecoDTO dto = new RetornoEnderecoDTO();

        dto.setId(endereco.getId());
        dto.setBairro(endereco.getBairro());
        dto.setCep(endereco.getCep());
        dto.setCidade(endereco.getCidade());
        dto.setComplemento(endereco.getComplemento());
        dto.setEstado(endereco.getEstado());
        dto.setNumero(endereco.getNumero());
        dto.setRua(endereco.getLogradouro());
        dto.setEvento(eventoMapper.toRetornoEventoDTO(endereco.getEvento()));

        return dto;
    }
}
