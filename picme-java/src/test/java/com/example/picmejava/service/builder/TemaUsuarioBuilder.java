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

        cadastroTemaFotografoDTO.setIdFotografo(1);
        cadastroTemaFotografoDTO.setTemas(List.of(
                TemaBuilder.criarTema()
        ));

        return cadastroTemaFotografoDTO;
    }

    public static CadastroTemaClienteDTO criarCadastroTemaCliente() {
        CadastroTemaClienteDTO cadastro = new CadastroTemaClienteDTO();

        cadastro.setIdCliente(1);
        cadastro.setTemas(List.of(
                TemaBuilder.criarTema()
        ));
        return cadastro;
    }
}
