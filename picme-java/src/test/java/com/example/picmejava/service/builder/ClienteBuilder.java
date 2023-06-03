package com.example.picmejava.service.builder;


import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import com.example.picmejava.model.dto.LoginUsuarioDTO;


import java.time.LocalDate;

public class ClienteBuilder {


    public ClienteBuilder() {
        throw new IllegalStateException("Classe utilitária");
    }


    public static Cliente criarCliente(){
        Cliente cliente = new Cliente();

        cliente.setId(1);
        cliente.setAutenticado(false);
        cliente.setSenha("senha");

        cliente.setCpf("99999999922");
        cliente.setNome("Cliente-1");
        cliente.setEmail("cliente@email");
        cliente.setDataCadastro(LocalDate.now());
        cliente.setDataNasc(LocalDate.ofYearDay(2004, 23));
        cliente.setNumCelular("(11)123412345");
        cliente.setTipoUsuario("cliente");

        cliente.setCpf("12345678912");
        cliente.setNome("Fotografo 01");
        cliente.setEmail("fotografo@email");
        cliente.setDataCadastro(LocalDate.now());
        cliente.setDataNasc(LocalDate.ofYearDay(1999, 12));
        cliente.setNumCelular("(11)123412345");
        cliente.setTipoUsuario("fotografo");


        return cliente;
    }


    public static CadastroUsuarioDTO criarCadastroUsuarioDto(){
        CadastroUsuarioDTO cadastroDto = new CadastroUsuarioDTO();

        cadastroDto.setNome("Cliente 01");
        cadastroDto.setSenha("senha");
        cadastroDto.setCpf("99999999922");
        cadastroDto.setNumCelular("(11)123412345");
        cadastroDto.setDataNasc(LocalDate.ofYearDay(2004,23));
        cadastroDto.setEmail("cliente@email");

        return cadastroDto;
    }

    public static LoginUsuarioDTO criarLoginUsuarioDto() {
        LoginUsuarioDTO loginDto = new LoginUsuarioDTO();

        loginDto.setEmail("email@email.com");
        loginDto.setSenha("senha");

        return loginDto;

    }

}
