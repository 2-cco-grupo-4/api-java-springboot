package com.example.picmejava;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Usuario{

    private List<Preferencia> preferencias;

    public Cliente(String nome, String email, String senha, String cpf, Date dataNasc, String numCelular, Boolean autenticado) {
        super(nome, email, senha, cpf, dataNasc, numCelular, autenticado);
        this.preferencias = new ArrayList<>();
    }

    UsuarioController controller = new UsuarioController();

    //METODOS

    //LOGIN
    @Override
    public String login(Usuario usuario) {
        usuario.setAutenticado(true);
        return "Usuário logado!";
    }

    //LOGOFF
    @Override
    public String logoff(Usuario usuario) {
        return null;
    }

}
