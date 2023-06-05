package com.example.picmejava.service.evento.dto;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Endereco;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CadastroEventoDTO {

    private LocalDate dataRealizacao;
    private String statusEvento;
    private Double valor;
    private Long idFotografo;
    private Long idTema;
    private Long idCliente;
    private Long idEndereco;
}