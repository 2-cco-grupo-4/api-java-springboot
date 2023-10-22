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
    public RetornoEventoDTO cadastrarExterno(CadastroSessaoExternoDTO cadastroSessaoExternoDTO){

        Fotografo fotografo = getFotografo(cadastroSessaoExternoDTO.getIdFotografo());
        CadastroClienteExternoDTO cadastroClienteExternoDTO = new CadastroClienteExternoDTO(cadastroSessaoExternoDTO.getCliente(), cadastroSessaoExternoDTO.getTelefone());
        Cliente cliente = clienteRepository.save((clienteMapper.clientExternoToCliente(cadastroClienteExternoDTO)));
        CadastroSessaoDTO cadastroSessaoDTO = new CadastroSessaoDTO(cadastroSessaoExternoDTO.getDataRealizacao(), cadastroSessaoExternoDTO.getStatusSessao(), cliente.getId(), fotografo.getId(), null, null, LocalDateTime.now());

        Sessao sessao = sessaoMapper.toEvento(fotografo, cliente, null, cadastroSessaoDTO);
        sessaoRepository.save(sessao);

        CadastroEnderecoExternoDTO cadastroEnderecoExternoDTO = new CadastroEnderecoExternoDTO(cadastroSessaoExternoDTO.getEndereco(), cadastroSessaoExternoDTO.getCidade(), cadastroSessaoExternoDTO.getBairro(), cadastroSessaoExternoDTO.getEstado(), cadastroSessaoExternoDTO.getComplemento(), cadastroSessaoExternoDTO.getCep(), sessao.getId());

        Endereco endereco  = enderecoRepository.save(enderecoMapper.fromEnderecoExternoToEndereco(cadastroEnderecoExternoDTO, sessao));

        sessao.setEndereco(enderecoRepository.getReferenceById(endereco.getId()));
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

    @Operation(summary = "Listar eventos por fotografo")
    public List<RetornoEventoDTO> listarPorFotografo(Long idFotografo) {
        Query query = entityManager.createNativeQuery("SELECT * FROM tb_sessao WHERE fk_fotografo = :idFotografo");
        query.setParameter("idFotografo", idFotografo);
        List<Object[]> sessoes = query.getResultList();

        List<RetornoEventoDTO> listRetorno = new ArrayList<>();

        for (Object[] sessao : sessoes) {
            RetornoEventoDTO retornoEventoDTO = new RetornoEventoDTO();
            retornoEventoDTO.setId((Long) sessao[0]);
            retornoEventoDTO.setDataRealizacao((LocalDateTime) sessao[1]);
            retornoEventoDTO.setStatusSessao((String) sessao[2]);
            retornoEventoDTO.setCreatedAt((LocalDateTime) sessao[3]);
            retornoEventoDTO.setCliente(clienteMapper.toPerfilClienteDTO(getCliente((Long) sessao[4])));
            retornoEventoDTO.setFotografo(fotografoMapper.toPerfilFotogradoDTO(getFotografo((Long) sessao[5])));
            retornoEventoDTO.setTema(TemaMapper.toPerfilTemaDTO(getTema((Long) sessao[6])));
            retornoEventoDTO.setEndereco(EnderecoMapper.toPerfilEnderecoDTO(getEndereco((Long) sessao[7])));
            listRetorno.add(retornoEventoDTO);
        }

        return listRetorno;
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
