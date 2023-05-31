package com.example.picmejava.service.builder;

import com.example.picmejava.model.Endereco;
import com.example.picmejava.model.Evento;
import com.example.picmejava.model.dto.PerfilEnderecoDTO;

import java.util.List;

public class EnderecoBuilder {

    public EnderecoBuilder() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static Endereco criarEndereco(){
        Endereco endereco = new Endereco();

        endereco.setId(1);
        endereco.setCep("09321333");
        endereco.setBairro("Bairro endereço 01");
        endereco.setCidade("Cidade endereço 01");
        endereco.setEstado("Estado cidade 01");
        endereco.setComplemento("Complemento endereço 01");
        endereco.setLogradouro("Logradouro endereço 01");
        endereco.setEvento(EventoBuilder.criarEvento());

        return endereco;
    }

    public static PerfilEnderecoDTO criarPerfilEndereco(){
        PerfilEnderecoDTO perfilEndereco = new PerfilEnderecoDTO();

        perfilEndereco.setId(1);
        perfilEndereco.setBairro("Bairro 01");
        perfilEndereco.setEstado("Estado 01");
        perfilEndereco.setCep("Cep 01");
        perfilEndereco.setCidade("Cidade 01");

        return perfilEndereco;
    }

    public static List<Endereco> criarListarEndereco(){
        return List.of(
                criarEndereco(),
                criarEndereco(),
                criarEndereco()
        );
    }
}
