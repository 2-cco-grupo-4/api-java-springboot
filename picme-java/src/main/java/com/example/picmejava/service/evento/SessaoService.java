package com.example.picmejava.service.evento;

import com.example.picmejava.model.*;
import com.example.picmejava.model.mapper.*;
import com.example.picmejava.service.evento.dto.*;
import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
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
    private final PagamentoRepository pagamentoRepository;
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
            PagamentoRepository pagamentoRepository,
            EntityManager entityManager) {
        this.sessaoRepository = sessaoRepository;
        this.clienteRepository = clienteRepository;
        this.fotografoRepository = fotografoRepository;
        this.enderecoRepository = enderecoRepository;
        this.temaRepository = temaRepository;
        this.pagamentoRepository = pagamentoRepository;
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

    public RetornoEventoDTO cadastrarContrato(CadastroContratoDTO novoContrato) {
        try {
            Fotografo fotografo = getFotografo(novoContrato.getIdFotografo());
            Cliente cliente = getCliente(novoContrato.getIdCliente());
            Tema tema = getTema(novoContrato.getIdTema());

            Sessao contrato = sessaoMapper.toContrato(fotografo, cliente, tema, novoContrato);
            sessaoRepository.save(contrato);

            return sessaoMapper.toRetornoEventoDTO(contrato);
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Entidade não encontrada no service: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Erro ao processar a requisição no service: " + e.getMessage());
            throw new RuntimeException("Erro ao processar a requisição");
        }
    }

    @Operation(summary = "Cadastrar pagamento para uma sessão")
    public RetornoPagamentoDTO cadastrarPagamento(CadastrarPagamentoDTO cadastrarPagamentoDTO) {
        try {
            Sessao sessao = sessaoRepository.findById(cadastrarPagamentoDTO.getIdSessao())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Sessão não encontrada"));

            Pagamento pagamento = new Pagamento();
            pagamento.setForma(cadastrarPagamentoDTO.getForma());
            pagamento.setValor(cadastrarPagamentoDTO.getValor());
            pagamento.setParcelas(cadastrarPagamentoDTO.getParcelas());
            pagamento.setSessao(sessao);

            pagamentoRepository.save(pagamento);

            RetornoPagamentoDTO retornoPagamentoDTO = new RetornoPagamentoDTO();
            retornoPagamentoDTO.setForma(pagamento.getForma());

            return retornoPagamentoDTO;
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Entidade não encontrada no service: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Erro ao processar a requisição no service: " + e.getMessage());
            throw new RuntimeException("Erro ao processar a requisição");
        }
    }

    public RetornoEventoDTO editarContrato(Long idContrato, EditaSessaoDTO contratoAtualizado) {
        try {
            Sessao contrato = sessaoRepository.findById(idContrato)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Contrato não encontrado"));

            contrato.setDataRealizacao(contratoAtualizado.getDataRealizacao());
            contrato.setStatusSessao(contratoAtualizado.getStatusSessao());

            sessaoRepository.save(contrato);

            return sessaoMapper.toRetornoEventoDTO(contrato);
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Entidade não encontrada no service: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Erro ao processar a requisição no service: " + e.getMessage());
            throw new RuntimeException("Erro ao processar a requisição");
        }
    }

    @Operation(summary = "Editar pagamento e salvar no banco")
    @Transactional
    public RetornoPagamentoDTO editarPagamento(Long idPagamento, EditaPagamentoDTO pagamentoAtualizado) {
        try {
            Pagamento pagamento = pagamentoRepository.findById(idPagamento)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Pagamento não encontrado"));

            pagamento.setForma(pagamentoAtualizado.getForma());
            pagamento.setValor(pagamentoAtualizado.getValor());
            pagamento.setParcelas(pagamentoAtualizado.getParcelas());

            pagamentoRepository.save(pagamento);

            RetornoPagamentoDTO retornoPagamentoDTO = new RetornoPagamentoDTO();

            return retornoPagamentoDTO;
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Entidade não encontrada no service: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Erro ao processar a requisição no service: " + e.getMessage());
            throw new RuntimeException("Erro ao processar a requisição");
        }
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

    @Operation(summary = "Visualizar informações de uma sessão")
    public RetornoEventoDTO visualizarSessao(Long idSessao) {
        Sessao sessao = sessaoRepository.findById(idSessao)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Sessão não encontrada"));

        return sessaoMapper.toRetornoEventoDTO(sessao);
    }

    @Operation(summary = "Visualizar informações de um pagamento")
    public RetornoPagamentoDTO visualizarPagamento(Long idPagamento) {
        Pagamento pagamento = pagamentoRepository.findBySessaoId(idPagamento);

        return sessaoMapper.toRetornoPagamentoDTO(pagamento);
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
