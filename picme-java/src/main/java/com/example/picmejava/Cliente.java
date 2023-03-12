package com.example.picmejava;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Usuario{

    private List<Preferencia> preferencias;

    public Cliente(String nome, String email, String senha, String cpf, LocalDate dataNasc, String numCelular, Boolean autenticado) {
        super(nome, email, senha, cpf, dataNasc, numCelular, autenticado);
        this.preferencias = new ArrayList<>();
    }

    //METODOS

    //GETTERS AND SETTERS

    public List<Preferencia> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<Preferencia> preferencias) {
        this.preferencias = preferencias;
    }
}
