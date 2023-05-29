package com.example.picmejava.model.dto;

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
    private Integer avaliacao;
    private Integer idFotografo;
    private Integer idTema;
    private Integer idCliente;
    private Integer idEndereco;
}
