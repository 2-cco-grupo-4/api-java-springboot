package com.example.picmejava.dto;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Usuario;

import java.util.List;

public class FotografoDTO {
    private Integer id;
    private String nome;
    private String dataNasc;
    private Boolean autenticado;
    private List<Album> albums;
    private String tipoUsuario;

    public FotografoDTO(Fotografo fotografo) {
        this.id = fotografo.getId();
        this.nome = fotografo.getNome();
        this.dataNasc = fotografo.getDataNasc();
        this.autenticado = fotografo.getAutenticado();
        this.tipoUsuario = fotografo.getTipoUsuario();
        this.albums = fotografo.getAlbuns();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Boolean getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
