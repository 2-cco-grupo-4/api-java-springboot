package com.example.picmejava.service.evento;

import com.example.picmejava.model.*;
import com.example.picmejava.model.mapper.*;
import com.example.picmejava.service.endereco.dto.CadastroEnderecoExternoDTO;
import com.example.picmejava.service.evento.dto.CadastroSessaoDTO;
import com.example.picmejava.service.evento.dto.CadastroSessaoExternoDTO;
import com.example.picmejava.service.evento.dto.RetornoEventoDTO;
import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.repository.*;
import com.example.picmejava.service.usuario.dto.CadastroClienteExternoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Tag(name = "Sessao Service", description = "APIs relacionadas a operações de eventos")
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final ClienteRepository clienteRepository;
    private final FotografoRepository fotografoRepository;
    private final EnderecoRepository enderecoRepository;
    private final TemaRepository temaRepository;
    private EntityManager entityManager;
    private final SessaoMapper sessaoMapper = new SessaoMapper();
    private final ClienteMapper clienteMapper = new ClienteMapper();
    private final FotografoMapper fotografoMapper = new FotografoMapper();
    private final EnderecoMapper enderecoMapper = new EnderecoMapper();

    @Autowired
    public SessaoService(
            SessaoRepository sessaoRepository,
            ClienteRepository clienteRepository,
            FotografoRepository fotografoRepository,
            EnderecoRepository enderecoRepository,
            TemaRepository temaRepository,
            EntityManager entityManager) {
        this.sessaoRepository = sessaoRepository;
        this.clienteRepository = clienteRepository;
        this.fotografoRepository = fotografoRepository;
        this.enderecoRepository = enderecoRepository;
        this.temaRepository = temaRepository;
        this.entityManager = entityManager;
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

    @Operation(summary = "Cadastrar sessão externa")
    public Sessao cadastrarExterno(CadastroSessaoExternoDTO cadastroSessaoExternoDTO) {

        Fotografo fotografo = getFotografo(cadastroSessaoExternoDTO.getIdFotografo());

        Cliente cliente = new Cliente();
        cliente.setNome(cadastroSessaoExternoDTO.getCliente());

        clienteRepository.save(cliente);

        Sessao sessao = new Sessao();
        sessao.setCliente(cliente);
        sessao.setStatusSessao(cadastroSessaoExternoDTO.getStatusSessao());
        sessao.setDataRealizacao(cadastroSessaoExternoDTO.getDataRealizacao());
        sessao.setFotografo(fotografo);
        sessao.setCliente(cliente);

        sessaoRepository.save(sessao);

        Endereco endereco = new Endereco();


        endereco.setCep(cadastroSessaoExternoDTO.getCep());
        endereco.setEstado(cadastroSessaoExternoDTO.getEstado());
        endereco.setCidade(cadastroSessaoExternoDTO.getCidade());
        endereco.setBairro(cadastroSessaoExternoDTO.getBairro());
        endereco.setLogradouro(cadastroSessaoExternoDTO.getEndereco());
        endereco.setComplemento(cadastroSessaoExternoDTO.getComplemento());
        endereco.setNumero("123");
        endereco.setSessao(sessao);

        enderecoRepository.save(endereco);


        return sessao;
    }


    @Operation(summary = "Listar todos os eventos")
    public List<RetornoEventoDTO> listar() {
        List<Sessao> sessoes = sessaoRepository.findAll();
        return sessoes.stream()
                .map(evento -> sessaoMapper.toRetornoEventoDTO(evento))
                .toList();
    }

    @Operation(summary = "Listar eventos por fotografo")
    public List<RetornoEventoDTO> listarPorFotografo(Long idFotografo) {
        List<Sessao> sessoes = sessaoRepository.findAllByFotografoId(idFotografo);
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

    private Endereco getEndereco(Long idEndereco) {
        return enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereco não encontrado"));
    }
}
