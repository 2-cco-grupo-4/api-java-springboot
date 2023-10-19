package com.example.picmejava.service.sessao.builder;

import com.example.picmejava.model.Sessao;
import com.example.picmejava.service.usuario.builder.FotografoBuilder;
import com.example.picmejava.service.tema.builder.TemaBuilder;
import com.example.picmejava.service.evento.dto.CadastroSessaoDTO;
import com.example.picmejava.service.usuario.builder.ClienteBuilder;

import java.time.LocalDate;
import java.util.List;

public class SessaoBuilder {

    public SessaoBuilder() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static Sessao criarEvento(){
        Sessao sessao = new Sessao();

        sessao.setTema(TemaBuilder.criarTema());
        sessao.setFotografo(FotografoBuilder.criarFotografo());
        sessao.setStatusSessao("Proposta");
        sessao.setId(1L);
        sessao.setCliente(ClienteBuilder.criarCliente());
        sessao.setDataRealizacao(LocalDate.now());

        return sessao;
    }

    public static CadastroSessaoDTO criarCadastroEvento(){
        CadastroSessaoDTO cadastroSessaoDTO = new CadastroSessaoDTO();

        cadastroSessaoDTO.setStatusSessao("Proposta");
        cadastroSessaoDTO.setDataRealizacao(LocalDate.now());
        cadastroSessaoDTO.setIdCliente(1L);
        cadastroSessaoDTO.setIdTema(1L);
        cadastroSessaoDTO.setIdEndereco(1L);
        cadastroSessaoDTO.setIdCliente(1L);

        return cadastroSessaoDTO;
    }

    public static List<Sessao> criarListaEvento(){
        return List.of(
                criarEvento(),
                criarEvento(),
                criarEvento()
        );
    }
}
