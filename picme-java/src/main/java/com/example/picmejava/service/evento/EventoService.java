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

    private final EventoRepository eventoRepository;
    private final ClienteRepository clienteRepository;
    private final FotografoRepository fotografoRepository;
    private final EnderecoRepository enderecoRepository;
    private final TemaRepository temaRepository;
    private final EventoMapper eventoMapper = new EventoMapper();

    @Autowired
    public EventoService(
            EventoRepository eventoRepository,
            ClienteRepository clienteRepository,
            FotografoRepository fotografoRepository,
            EnderecoRepository enderecoRepository,
            TemaRepository temaRepository) {
        this.eventoRepository = eventoRepository;
        this.clienteRepository = clienteRepository;
        this.fotografoRepository = fotografoRepository;
        this.enderecoRepository = enderecoRepository;
        this.temaRepository = temaRepository;
    }

    @Operation(summary = "Cadastrar um novo evento")
    public RetornoEventoDTO cadastrar(CadastroEventoDTO novoEvento) {
        Fotografo fotografo = getFotografo(novoEvento.getIdFotografo());
        Cliente cliente = getCliente(novoEvento.getIdCliente());
        Tema tema = getTema(novoEvento.getIdTema());

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

    private Fotografo getFotografo(Long idFotografo) {
        return fotografoRepository.findById(idFotografo)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fotografo não encontrado"));
    }

    private Cliente getCliente(Long idCliente) {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
    }

    private Tema getTema(Long idTema) {
        return temaRepository.findById(idTema)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não encontrado"));
    }
}
