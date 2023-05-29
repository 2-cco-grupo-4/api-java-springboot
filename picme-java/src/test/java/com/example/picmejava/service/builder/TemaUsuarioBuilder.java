package com.example.picmejava.service.builder;

import com.example.picmejava.model.dto.CadastroTemaClienteDTO;
import com.example.picmejava.model.dto.CadastroTemaFotografoDTO;
import com.example.picmejava.model.dto.RetornoTemaClienteDTO;
import com.example.picmejava.model.dto.RetornoTemaFotografoDTO;

import java.util.List;

public class TemaUsuarioBuilder {

    public TemaUsuarioBuilder() {
        throw new IllegalStateException("Classe utilitaria");
    }

    public static CadastroTemaFotografoDTO criarCadastroTemaFotografo(){
        CadastroTemaFotografoDTO cadastroTemaFotografoDTO = new CadastroTemaFotografoDTO();

        cadastroTemaFotografoDTO.setFotografo(FotografoBuilder.criarFotografo());
        cadastroTemaFotografoDTO.setTemas(List.of(
                TemaBuilder.criarTema()
        ));

        return cadastroTemaFotografoDTO;
    }

    public static CadastroTemaClienteDTO criarCadastroTemaCliente(){
        CadastroTemaClienteDTO cadastroTemaClienteDTO = new CadastroTemaClienteDTO();

        cadastroTemaClienteDTO.setCliente(ClienteBuilder.criarCliente());
        cadastroTemaClienteDTO.setTemas(List.of(
                TemaBuilder.criarTema()
        ));

        return cadastroTemaClienteDTO;
    }

    public static RetornoTemaFotografoDTO criarRetornoTemaFotografo(){
        RetornoTemaFotografoDTO retornoTemaFotografoDTO = new RetornoTemaFotografoDTO();

        retornoTemaFotografoDTO.setFotografo(FotografoBuilder.criarPerfilFotografoDto());
        retornoTemaFotografoDTO.setTemas(List.of(TemaBuilder.criarPerfilTemaDto()));
        return retornoTemaFotografoDTO;
    }

    public static RetornoTemaClienteDTO criarRetornoTemaCliente(){
        RetornoTemaClienteDTO retornoTemaClienteDTO = new RetornoTemaClienteDTO();

        retornoTemaClienteDTO.setCliente(ClienteBuilder.criarPerfilClienteDto());
        retornoTemaClienteDTO.setTemas(List.of(TemaBuilder.criarPerfilTemaDto()));
        return retornoTemaClienteDTO;
    }
}
