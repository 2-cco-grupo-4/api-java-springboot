package com.example.picmejava.model.dto;

import com.example.picmejava.model.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class PerfilEnderecoDTO {

    private Integer id;
    private String estado;
    private String cidade;
    private String cep;
    private String bairro;
    private String rua;
    private Integer numero;
    private String complemento;
}
