package com.example.picmejava.service.builder;

import com.example.picmejava.model.Tema;
import com.example.picmejava.model.Usuario;
import com.example.picmejava.model.dto.CadastroTemaDto;
import com.example.picmejava.model.dto.PerfilTemaDTO;

import java.util.List;

public class TemaBuilder {

    public TemaBuilder() {
        throw new IllegalStateException("Classe Utilitária");
    }

    public static Tema criarTema(){
        Tema tema = new Tema();
        tema.setNome("Tema 01");
        tema.setUsuarios(List.of(FotografoBuilder.criarFotografo()));
        return tema;
    }

    public static PerfilTemaDTO criarPerfilTemaDto(){
        PerfilTemaDTO perfilTemaDTO = new PerfilTemaDTO();

        perfilTemaDTO.setId(1L);
        perfilTemaDTO.setNome("Tema 01");
        return perfilTemaDTO;
    }

    public static List<Tema> criarTemas(){
        return List.of(
                new Tema(1L, "Tema 01", List.of(FotografoBuilder.criarFotografo())),
                new Tema(2L, "Tema 02", List.of(FotografoBuilder.criarFotografo())),
                new Tema(3L, "Tema 03", List.of(FotografoBuilder.criarFotografo()))
        );
    }

    public static CadastroTemaDto criarCadastroTemaDto() {
        CadastroTemaDto dto = new CadastroTemaDto();
        dto.setNome("Tema 01");
        return dto;
    }
}
