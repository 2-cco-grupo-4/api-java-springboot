package com.example.picmejava.service.endereco.builder;

import com.example.picmejava.model.Endereco;
import com.example.picmejava.service.sessao.builder.SessaoBuilder;
import com.example.picmejava.service.endereco.dto.CadastroEnderecoDTO;
import com.example.picmejava.service.endereco.dto.PerfilEnderecoDTO;

import java.util.List;

public class EnderecoBuilder {

    public EnderecoBuilder() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static Endereco criarEndereco(){
        Endereco endereco = new Endereco();

        endereco.setId(1L);
        endereco.setCep("09321333");
        endereco.setBairro("Bairro endereço 01");
        endereco.setCidade("Cidade endereço 01");
        endereco.setEstado("Estado cidade 01");
        endereco.setComplemento("Complemento endereço 01");
        endereco.setLogradouro("Logradouro endereço 01");
        endereco.setSessao(SessaoBuilder.criarEvento());

        return endereco;
    }

    public static PerfilEnderecoDTO criarPerfilEndereco(){
        PerfilEnderecoDTO perfilEndereco = new PerfilEnderecoDTO();

        perfilEndereco.setId(1L);
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

    public static CadastroEnderecoDTO criarCadastroEndereco() {
        CadastroEnderecoDTO dto = new CadastroEnderecoDTO();
        dto.setBairro("Bairro 01");
        dto.setCep("Cep 01");
        dto.setEstado("Estado 01");
        dto.setCidade("Cidade 01");
        dto.setComplemento("Complemento 01");
        dto.setNumero(01);
        dto.setLogradouro("Logradouro 01");
        dto.setIdEvento(1L);

        return dto;
    }
}
