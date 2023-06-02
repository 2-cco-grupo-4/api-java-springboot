package com.example.picmejava.service.builder;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.service.usuario.dto.PerfilClienteDTO;

import java.time.LocalDate;
import java.util.List;

public class ClienteBuilder {

    public ClienteBuilder() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static Cliente criarCliente(){
        Cliente cliente = new Cliente();

        cliente.setId(1L);
        cliente.setAutenticado(false);
        cliente.setSenha("senha");
        cliente.setCpf("12345678912");
        cliente.setNome("Fotografo 01");
        cliente.setEmail("fotografo@email");
        cliente.setDataCadastro(LocalDate.now());
        cliente.setDataNasc(LocalDate.ofYearDay(1999, 12));
        cliente.setNumCelular("(11)123412345");
        cliente.setTipoUsuario("fotografo");

        return cliente;
    }

    public static PerfilClienteDTO criarPerfilClienteDto(){
        PerfilClienteDTO perfilClienteDTO = new PerfilClienteDTO();

        perfilClienteDTO.setId(1L);
        perfilClienteDTO.setNome("Cliente 01");
        perfilClienteDTO.setAutenticado(false);
        perfilClienteDTO.setTipoUsuario("cliente");
        perfilClienteDTO.setTemas(List.of(TemaBuilder.criarPerfilTemaDto()));
        return perfilClienteDTO;
    }

}
