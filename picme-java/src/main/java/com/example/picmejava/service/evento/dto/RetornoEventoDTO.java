package com.example.picmejava.service.evento.dto;

import com.example.picmejava.service.tema.dto.PerfilTemaDTO;
import com.example.picmejava.service.endereco.dto.PerfilEnderecoDTO;
import com.example.picmejava.service.usuario.dto.PerfilClienteDTO;
import com.example.picmejava.service.usuario.dto.PerfilFotografoDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class RetornoEventoDTO {
    private Long id;
    private LocalDate dataRealizacao;
    private String statusSessao;
    private LocalDateTime createdAt;
    private PerfilFotografoDTO fotografo;
    private PerfilClienteDTO cliente;
    private PerfilEnderecoDTO endereco;
    private CadastrarPagamentoDTO pagamento;
    private PerfilTemaDTO tema;


}
