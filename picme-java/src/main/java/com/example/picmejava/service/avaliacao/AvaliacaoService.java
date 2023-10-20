package com.example.picmejava.service.avaliacao;

import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.Sessao;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.mapper.SessaoMapper;
import com.example.picmejava.model.utils.FilaObj;
import com.example.picmejava.model.utils.PilhaObj;
import com.example.picmejava.model.Avaliacao;
import com.example.picmejava.repository.AvaliacaoRepository;
import com.example.picmejava.repository.SessaoRepository;
import com.example.picmejava.service.avaliacao.dto.RetornoAvaliacaoDTO;
import com.example.picmejava.service.evento.dto.RetornoEventoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Avaliação Service", description = "APIs relacionadas a operações de avaliação")
@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    private SessaoMapper sessaoMapper = new SessaoMapper();
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SessaoRepository sessaoRepository;
    private PilhaObj<Avaliacao> pilha = new PilhaObj<>(10);

    @Operation(summary = "Realizar uma avaliação")
    public void avaliar(Avaliacao avaliacao) {
        avaliacaoRepository.save(avaliacao);
        pilha.push(avaliacao);
    }

    @Operation(summary = "Desfazer a última avaliação")
    public Avaliacao desfazer() {
        return pilha.pop();
    }

    @Operation(summary = "Exibir todas as avaliações")
    public List<RetornoAvaliacaoDTO> exibir(Long idFotografo) {

        Query query = entityManager.createNativeQuery("SELECT * FROM tb_avaliacao INNER JOIN tb_sessao ON tb_sessao.id_sessao = tb_avaliacao.fk_sessao INNER JOIN tb_usuario ON tb_sessao.fk_fotografo = tb_usuario.id_usuario WHERE fk_fotografo = :idFotografo");
        List<Object[]> resultado = query.setParameter("idFotografo", idFotografo).getResultList();

        List<RetornoAvaliacaoDTO> avaliacoes = new ArrayList<>();

        for(Object[] avaliacao : resultado) {

            Long idAvaliacao = (Long) avaliacao[0];
            String descricao = (String) avaliacao[1];
            Double nota = (Double) avaliacao[2];
            Long idSessao = (Long) avaliacao[3];
            Sessao sessao = getSessao(idSessao);
            RetornoEventoDTO retornoEventoDTO = sessaoMapper.toRetornoEventoDTO(sessao);

            avaliacoes.add(new RetornoAvaliacaoDTO(idAvaliacao, descricao, nota, retornoEventoDTO));

        }

        return avaliacoes;

    }

    private Sessao getSessao(Long idSessao) {
        return sessaoRepository.findById(idSessao)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Sessão não existe"));
    }

//    @Operation(summary = "Enfilera as avaliações")
//    public FilaObj<Avaliacao> enfileirarAvaliacoes() {
//        List<Avaliacao> avaliacoes = exibir();
//        FilaObj<Avaliacao> fila = new FilaObj<>(avaliacoes.size());
//
//        for (Avaliacao avaliacao : avaliacoes) {
//            fila.push(avaliacao);
//        }
//
//        return fila;
//    }

}
