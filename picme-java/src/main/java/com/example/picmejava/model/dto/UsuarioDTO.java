package com.example.picmejava.model.dto;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Usuario;

import java.util.List;

public class UsuarioDTO {
    private Integer id;
    private String nome;
    private String dataNasc;
    private Boolean autenticado;
    private String tipoUsuario;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.dataNasc = usuario.getDataNasc();
        this.autenticado = usuario.getAutenticado();
        this.tipoUsuario = usuario.getTipoUsuario();
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}