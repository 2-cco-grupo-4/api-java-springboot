package com.example.picmejava.service.builder;

import com.example.picmejava.service.temaUsuario.dto.CadastroTemaClienteDTO;
import com.example.picmejava.service.temaUsuario.dto.CadastroTemaFotografoDTO;

import java.util.List;

public class TemaUsuarioBuilder {

    public TemaUsuarioBuilder() {
        throw new IllegalStateException("Classe utilitaria");
    }

    public static CadastroTemaFotografoDTO criarCadastroTemaFotografo(){
        CadastroTemaFotografoDTO cadastroTemaFotografoDTO = new CadastroTemaFotografoDTO();

        cadastroTemaFotografoDTO.setIdFotografo(1L);
        cadastroTemaFotografoDTO.setTemas(List.of(
                TemaBuilder.criarTema()
        ));

        return cadastroTemaFotografoDTO;
    }

    public static CadastroTemaClienteDTO criarCadastroTemaCliente() {
        CadastroTemaClienteDTO cadastro = new CadastroTemaClienteDTO();

        cadastro.setIdCliente(1L);
        cadastro.setTemas(List.of(
                TemaBuilder.criarTema()
        ));
        return cadastro;
    }
}
