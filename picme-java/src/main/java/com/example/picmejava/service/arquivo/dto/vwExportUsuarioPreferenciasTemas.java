package com.example.picmejava.service.arquivo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class vwExportUsuarioPreferenciasTemas {

    private Long idUsuario;
    private String nomeUser;
    private LocalDate dataNascimento;
    private String celular;
    private LocalDateTime dataCadastro;
    private int tipoUsuario;
    private String cidadePreferencia;
    private String estadoPreferencia;
    private Long idTema;
    private String nomeTema;

}
