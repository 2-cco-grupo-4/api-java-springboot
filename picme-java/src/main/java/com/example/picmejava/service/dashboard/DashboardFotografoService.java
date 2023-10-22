package com.example.picmejava.service.dashboard;

import com.example.picmejava.service.dashboard.dto.fotografo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Tag(name = "Dashboard Fotógrafo", description = "Endpoints para informações do painel de controle")
public class DashboardFotografoService {

    @Autowired
    private EntityManager entityManager;

    @Operation(summary = "Obter valor médio cobrado por sessão", description = "Retorna o valor médio cobrado por sessão.")
    public List<vwKpiValorMedioCobrado> trazerValorMedioCobrado(Long idFotografo) {
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_kpi_valor_medio_cobrado WHERE Fotografo = :idFotografo");
        List<Object[]> resultado = query.setParameter("idFotografo", idFotografo).getResultList();

        List<vwKpiValorMedioCobrado> listVwKpiValorMedioCobrados = new ArrayList<>();

        for (Object[] linha : resultado) {
            Long fotografo = (Long) linha[0];
            BigDecimal media = (BigDecimal) linha[1];

            vwKpiValorMedioCobrado vwKpiValorMedioCobrado = new vwKpiValorMedioCobrado(fotografo, media);
            listVwKpiValorMedioCobrados.add(vwKpiValorMedioCobrado);
        }

        return listVwKpiValorMedioCobrados;
    }

    @Operation(summary = "Contar sessões no mês", description = "Retorna a contagem de sessões no mês.")
    public List<vwKpiSessoesAgendadasMes> trazerQuantidadeSessoesMes(Long idFotografo) {
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_kpi_sessoes_agendadas_mes WHERE fotografo = :idFotografo");
        List<Object[]> resultado = query.setParameter("idFotografo", idFotografo).getResultList();

        List<vwKpiSessoesAgendadasMes> listVwKpiSessoesAgendadasMes = new ArrayList<>();

        for (Object[] linha : resultado) {
            Long fotografo = (Long) linha[0];
            Long total = (Long) linha[1];

            vwKpiSessoesAgendadasMes vwKpiSessoesAgendadasMes = new vwKpiSessoesAgendadasMes(fotografo, total);
            listVwKpiSessoesAgendadasMes.add(vwKpiSessoesAgendadasMes);
        }

        return listVwKpiSessoesAgendadasMes;
    }

    @Operation(summary = "Contar propostas de trabalho recebidas no mês atual e anterior", description = "Retorna a contagem de propostas de trabalho recebidas no mês atual e anterior.")
    public List<vwKpiPropostasRecebidasMesAtual> trazerPropostasRecebidasMes(Long idFotografo) {
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_kpi_propostas_recebidas_mes_atual WHERE fotografo = :idFotografo");
        List<Object[]> resultado = query.setParameter("idFotografo", idFotografo).getResultList();

        List<vwKpiPropostasRecebidasMesAtual> listVwKpiPropostasRecebidasMesAtual = new ArrayList<>();

        for (Object[] linha : resultado) {
            Long fotografo = (Long) linha[0];
            String mesAtual = (String) linha[1];
            Long mesAtualTotal = (Long) linha[2];
            String mesAnterior = (String) linha[3];
            Long mesAnteriorTotal = (Long) linha[4];

            vwKpiPropostasRecebidasMesAtual vwKpiPropostasRecebidasMes = new vwKpiPropostasRecebidasMesAtual(fotografo, mesAtual, mesAtualTotal, mesAnterior, mesAnteriorTotal);
            listVwKpiPropostasRecebidasMesAtual.add(vwKpiPropostasRecebidasMes);
        }

        return listVwKpiPropostasRecebidasMesAtual;
    }

    @Operation(summary = "Obter a variação do lucro por mês", description = "Retorna a variação do lucro por mês.")
    public List<vwKpiVariacaoLucroMensal> trazerVariacaoLucroMensal(Long idFotografo) {
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_kpi_variacao_lucro_mensal WHERE fotografo = :idFotografo");
        List<Object[]> resultado = query.setParameter("idFotografo", idFotografo).getResultList();

        List<vwKpiVariacaoLucroMensal> listVwKpiVariacaoLucroMensal = new ArrayList<>();

        for (Object[] linha : resultado) {
            Long fotografo = (Long) linha[0];
            BigDecimal atual = (BigDecimal) linha[1];
            BigDecimal passado = (BigDecimal) linha[2];

            vwKpiVariacaoLucroMensal vwKpiVariacaoLucroMensal = new vwKpiVariacaoLucroMensal(fotografo, atual, passado);
            listVwKpiVariacaoLucroMensal.add(vwKpiVariacaoLucroMensal);
        }

        return listVwKpiVariacaoLucroMensal;
    }

    @Operation(summary = "Obter a média de avaliação por tema", description = "Retorna a média de avaliação por tema.")
    public List<vwMediaAvaliacaoPorTema> trazerMediaAvaliacaoTema(Long idFotografo) {
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_media_avaliacao_por_tema WHERE user = :idFotografo");
        List<Object[]> resultado = query.setParameter("idFotografo", idFotografo).getResultList();

        List<vwMediaAvaliacaoPorTema> listVwMediaAvaliacaoPorTema = new ArrayList<>();

        for (Object[] linha : resultado) {
            String tema = (String) linha[0];
            Double media = (Double) linha[1];
            Long user = (Long) linha[2];

            vwMediaAvaliacaoPorTema vwMediaAvaliacaoPorTema = new vwMediaAvaliacaoPorTema(tema, media, user);
            listVwMediaAvaliacaoPorTema.add(vwMediaAvaliacaoPorTema);
        }

        return listVwMediaAvaliacaoPorTema;
    }

    @Operation(summary = "Obter a variação do lucro nos últimos meses", description = "Retorna a variação do lucro nos últimos meses.")
    public List<vwVariacaoLucroUltimosMeses> trazerVariacaoLucroUltimosMeses(Long idFotografo) {
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_variacao_lucro_ultimos_meses WHERE user = :idFotografo");
        List<Object[]> resultado = query.setParameter("idFotografo", idFotografo).getResultList();

        List<vwVariacaoLucroUltimosMeses> listVwVariacaoLucroUltimosMeses = new ArrayList<>();

        for (Object[] linha : resultado) {
            String mes = (String) linha[0];
            BigDecimal lucro = (BigDecimal) linha[1];
            Long user = (Long) linha[2];

            vwVariacaoLucroUltimosMeses vwVariacaoLucroUltimosMeses = new vwVariacaoLucroUltimosMeses(mes, lucro, user);
            listVwVariacaoLucroUltimosMeses.add(vwVariacaoLucroUltimosMeses);
        }

        return listVwVariacaoLucroUltimosMeses;
    }

    @Operation(summary = "Obter a quantidade de contatos que foram convertidos em sessões", description = "Retorna a quantidade de contatos que foram convertidos em sessões.")
    public List<vwContatosConvertidosSessoes> trazerContatosConvertidosSessoes(Long idFotografo) {
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_contatos_convertidos_sessao WHERE user = :idFotografo");
        List<Object[]> resultado = query.setParameter("idFotografo", idFotografo).getResultList();

        List<vwContatosConvertidosSessoes> listVwContatosConvertidosSessoes = new ArrayList<>();

        for (Object[] linha : resultado) {
            String mes = (String) linha[0];
            Long convertidas = (Long) linha[1];
            Long total = (Long) linha[2];
            Long interrompidas = (Long) linha[3];
            Long user = (Long) linha[4];

            vwContatosConvertidosSessoes vwContatosConvertidosSessoes = new vwContatosConvertidosSessoes(mes, convertidas, total, interrompidas, user);
            listVwContatosConvertidosSessoes.add(vwContatosConvertidosSessoes);
        }

        return listVwContatosConvertidosSessoes;
    }

}
