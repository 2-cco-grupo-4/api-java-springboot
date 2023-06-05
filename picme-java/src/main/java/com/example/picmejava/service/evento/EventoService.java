package com.example.picmejava.service.evento;

import com.example.picmejava.model.*;
import com.example.picmejava.service.evento.dto.CadastroEventoDTO;
import com.example.picmejava.service.evento.dto.RetornoEventoDTO;
import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.EventoMapper;
import com.example.picmejava.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Service
@Tag(name = "Evento Service", description = "APIs relacionadas a operações de eventos")
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FotografoRepository fotografoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private TemaRepository temaRepository;

    EventoMapper eventoMapper = new EventoMapper();

    @Operation(summary = "Cadastrar um novo evento")
    public RetornoEventoDTO cadastrar(CadastroEventoDTO novoEvento) {
        Fotografo fotografo = fotografoRepository.findById(novoEvento.getIdFotografo())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fotografo não encontrado"));

        Cliente cliente = clienteRepository.findById(novoEvento.getIdCliente())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));

        Tema tema = temaRepository.findById(novoEvento.getIdTema())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não encontrado"));

        Evento evento = eventoMapper.toEvento(fotografo, cliente, tema, novoEvento);
        eventoRepository.save(evento);

        return eventoMapper.toRetornoEventoDTO(evento);
    }

    @Operation(summary = "Listar todos os eventos")
    public List<RetornoEventoDTO> listar() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventos.stream()
                .map(evento -> eventoMapper.toRetornoEventoDTO(evento))
                .toList();
    }
}