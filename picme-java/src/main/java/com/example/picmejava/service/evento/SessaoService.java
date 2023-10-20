package com.example.picmejava.service.evento;

import com.example.picmejava.model.*;
import com.example.picmejava.service.evento.dto.CadastroSessaoDTO;
import com.example.picmejava.service.evento.dto.RetornoEventoDTO;
import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.SessaoMapper;
import com.example.picmejava.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Service
@Tag(name = "Sessao Service", description = "APIs relacionadas a operações de eventos")
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final ClienteRepository clienteRepository;
    private final FotografoRepository fotografoRepository;
    private final EnderecoRepository enderecoRepository;
    private final TemaRepository temaRepository;
    private final SessaoMapper sessaoMapper = new SessaoMapper();

    @Autowired
    public SessaoService(
            SessaoRepository sessaoRepository,
            ClienteRepository clienteRepository,
            FotografoRepository fotografoRepository,
            EnderecoRepository enderecoRepository,
            TemaRepository temaRepository) {
        this.sessaoRepository = sessaoRepository;
        this.clienteRepository = clienteRepository;
        this.fotografoRepository = fotografoRepository;
        this.enderecoRepository = enderecoRepository;
        this.temaRepository = temaRepository;
    }

    @Operation(summary = "Cadastrar um novo evento")
    public RetornoEventoDTO cadastrar(CadastroSessaoDTO novoEvento) {
        Fotografo fotografo = getFotografo(novoEvento.getIdFotografo());
        Cliente cliente = getCliente(novoEvento.getIdCliente());
        Tema tema = getTema(novoEvento.getIdTema());

        Sessao sessao = sessaoMapper.toEvento(fotografo, cliente, tema, novoEvento);
        sessaoRepository.save(sessao);

        return sessaoMapper.toRetornoEventoDTO(sessao);
    }

    @Operation(summary = "Listar todos os eventos")
    public List<RetornoEventoDTO> listar() {
        List<Sessao> sessoes = sessaoRepository.findAll();
        return sessoes.stream()
                .map(evento -> sessaoMapper.toRetornoEventoDTO(evento))
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
