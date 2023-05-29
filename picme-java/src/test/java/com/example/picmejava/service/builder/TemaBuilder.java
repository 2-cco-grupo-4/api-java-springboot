package com.example.picmejava.service.builder;

import com.example.picmejava.model.Tema;
import com.example.picmejava.model.Usuario;

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

    public static List<Tema> criarTemas(){
        return List.of(
                new Tema(1, "Tema 01", List.of(FotografoBuilder.criarFotografo())),
                new Tema(2, "Tema 02", List.of(FotografoBuilder.criarFotografo())),
                new Tema(3, "Tema 03", List.of(FotografoBuilder.criarFotografo()))
        );
    }

}
