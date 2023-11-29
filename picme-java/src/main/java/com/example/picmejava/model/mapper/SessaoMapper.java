package com.example.picmejava.model.mapper;

import com.example.picmejava.model.*;
import com.example.picmejava.service.evento.dto.*;

public class SessaoMapper {

    ClienteMapper clienteMapper = new ClienteMapper();
    FotografoMapper fotografoMapper = new FotografoMapper();

    public RetornoEventoDTO toRetornoEventoDTO(Sessao novaSessao) {
        RetornoEventoDTO dto = new RetornoEventoDTO();

        dto.setId(novaSessao.getId());
        dto.setStatusSessao(novaSessao.getStatusSessao());
        dto.setDataRealizacao(novaSessao.getDataRealizacao());
        dto.setCliente(clienteMapper.toPerfilClienteDTO(novaSessao.getCliente()));
        dto.setFotografo(fotografoMapper.toPerfilFotogradoDTO(novaSessao.getFotografo()));
        if (novaSessao.getEndereco() != null){
            dto.setEndereco(EnderecoMapper.toPerfilEnderecoDTO(novaSessao.getEndereco()));
        }
        if (novaSessao.getPagamento() != null){
            dto.setPagamento(toCadastrarPagamentoDTO(novaSessao.getPagamento()));
        }

        return dto;
    }


    public Sessao toEvento(Fotografo fotografo, Cliente cliente, Tema tema, CadastroSessaoDTO dadosSessao) {
        Sessao sessao = new Sessao();

        sessao.setCliente(cliente);
        sessao.setFotografo(fotografo);
        sessao.setTema(tema);
        sessao.setStatusSessao(dadosSessao.getStatusSessao());
        sessao.setDataRealizacao(dadosSessao.getDataRealizacao());
        sessao.setCreatedAt(dadosSessao.getCreatedAt());

        return sessao;
    }

    public CadastrarPagamentoDTO toCadastrarPagamentoDTO(Pagamento pagamento) {
        CadastrarPagamentoDTO dto = new CadastrarPagamentoDTO();

        dto.setForma(pagamento.getForma());
        dto.setValor(pagamento.getValor());
        dto.setParcelas(pagamento.getParcelas());

        return dto;
    }

    public PerfilEventoDTO toPerfilSessaoDTO(Sessao sessao){
        PerfilEventoDTO dto = new PerfilEventoDTO();

        dto.setId(sessao.getId());
        dto.setStatusEvento(sessao.getStatusSessao());
        dto.setDataRealizacao(sessao.getDataRealizacao());
        dto.setCreatedAt(sessao.getCreatedAt());
        dto.setCliente(clienteMapper.toPerfilClienteDTO(sessao.getCliente()));
        dto.setFotografo(fotografoMapper.toPerfilFotogradoDTO(sessao.getFotografo()));

        return dto;
    }

    public CadastroSessaoDTO fromCadastroExternoToCadastroSessaoDTO(CadastroSessaoExternoDTO cadastroSessaoExternoDTO, Cliente cliente, Endereco endereco) {
        CadastroSessaoDTO cadastroSessaoDTO = new CadastroSessaoDTO();

        cadastroSessaoDTO.setStatusSessao(cadastroSessaoExternoDTO.getStatusSessao());
        cadastroSessaoDTO.setDataRealizacao(cadastroSessaoExternoDTO.getDataRealizacao());
        cadastroSessaoDTO.setIdCliente(cliente.getId());
        cadastroSessaoDTO.setIdFotografo(cadastroSessaoExternoDTO.getIdFotografo());
        cadastroSessaoDTO.setIdEndereco(endereco.getId());
        cadastroSessaoDTO.setIdTema(null);

        return cadastroSessaoDTO;
    }

    public Sessao toContrato(Fotografo fotografo, Cliente cliente, Tema tema, CadastroContratoDTO contratoDTO) {
        Sessao contrato = new Sessao();

        contrato.setCliente(cliente);
        contrato.setFotografo(fotografo);
        contrato.setTema(tema);
        contrato.setStatusSessao(contratoDTO.getStatusSessao());
        contrato.setDataRealizacao(contratoDTO.getDataRealizacao());
        contrato.setCreatedAt(contratoDTO.getCreatedAt());

        return contrato;
    }

    public CadastroSessaoDTO fromContratoToCadastroSessaoDTO(Sessao contrato) {
        CadastroSessaoDTO cadastroSessaoDTO = new CadastroSessaoDTO();

        cadastroSessaoDTO.setStatusSessao(contrato.getStatusSessao());
        cadastroSessaoDTO.setDataRealizacao(contrato.getDataRealizacao());
        cadastroSessaoDTO.setIdCliente(contrato.getCliente().getId());
        cadastroSessaoDTO.setIdFotografo(contrato.getFotografo().getId());
        cadastroSessaoDTO.setIdTema(contrato.getTema().getId());
        cadastroSessaoDTO.setCreatedAt(contrato.getCreatedAt());

        return cadastroSessaoDTO;
    }
}
