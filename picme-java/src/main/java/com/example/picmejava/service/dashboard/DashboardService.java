package com.example.picmejava.service.dashboard;

import com.example.picmejava.service.dashboard.dto.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

@Service
@Tag(name = "Dashboard", description = "Endpoints para informações do painel de controle")
public class DashboardService {

    @Autowired
    private EntityManager entityManager;

    @Operation(summary = "Obter faixa etária dos clientes", description = "Retorna a contagem de clientes por faixa etária.")
    public List<vwFaixaEtariaCliente> trazerFaixaEtariaCliente(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_faixa_etaria_cliente");
        List<Object[]> resultado = query.getResultList();

        List<vwFaixaEtariaCliente> faixaEtariaClienteDtos = new ArrayList<>();

        for (Object[] linha : resultado) {
            String faixa = (String) linha[0];
            Long quantidade = (Long) linha[1];

            vwFaixaEtariaCliente faixaEtariaClienteDto = new vwFaixaEtariaCliente(faixa, quantidade);
            faixaEtariaClienteDtos.add(faixaEtariaClienteDto);
        }

        return faixaEtariaClienteDtos;
    }

    @Operation(summary = "Obter contagem de contatos por tema", description = "Retorna a contagem de contatos por tema.")
    public List<vwTemaCountSessoes> trazerContagemTemaContato() {
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_tema_count_sessoes");
        List<Object[]> resultado = query.getResultList();

        List<vwTemaCountSessoes> temaContatosClienteDtos = new ArrayList<>();

        for (Object[] linha : resultado) {
            String tema = (String) linha[0];
            Long contatos = (Long) linha[1];


            vwTemaCountSessoes temaContatosClienteDto = new vwTemaCountSessoes(tema, contatos);
            temaContatosClienteDtos.add(temaContatosClienteDto);
        }

        return temaContatosClienteDtos;
    }

    @Operation(summary = "Obter contagem de clientes que fecharam acordo em acordo em uma semana", description = "Retorna a contagem de clientes em acordo em uma semana.")
    public List<vwClientesImediatosMes> trazerContagemClientesAcordoUmaSemana() {
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_clientes_imediatos_mes");
        List<Object[]> resultado = query.getResultList();

        List<vwClientesImediatosMes> listaResponse = new ArrayList<>();

        for (Object[] linha : resultado) {
            String mes = (String) linha[0];
            Long agendaram = (Long) linha[1];
            Long total = (Long) linha[2];
            Long naoAgendaram = (Long) linha[3];

            vwClientesImediatosMes response = new vwClientesImediatosMes(mes, agendaram, total, naoAgendaram);
            listaResponse.add(response);
        }

        return listaResponse;
    }
    @Operation(summary = "Obter total de clientes e fotógrafos", description = "Retorna a contagem total de clientes e de fotógrafos.")
    public List<vwClientesFotografos> totalClientesFotografos(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_clientes_fotografos");
        List<Object[]> resultado = query.getResultList();

        List<vwClientesFotografos> clientesFotografosDTO = new ArrayList<>();

        for (Object[] linha : resultado) {
            String mes = (String) linha[0];
            Long clientes = (Long) linha[1];
            Long total = (Long) linha[2];
            Long fotografos = (Long) linha[3];

            vwClientesFotografos clientesFotografos = new vwClientesFotografos(mes, clientes, total, fotografos);
            clientesFotografosDTO.add(clientesFotografos);
        }

        return clientesFotografosDTO;
    }

    @Operation(summary = "Progressão de usuários por mês", description = "Retorna a progressão da quantidade de novos usuários cadastrados nos últimos 6 meses")
    public List<vwProgressaoCadastroUsuarios> progressaoUsuariosMes(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_progressao_cadastro_usuarios");
        List<Object[]> resultado = query.getResultList();

        List<vwProgressaoCadastroUsuarios> listProgressaoCadastroUsuarios = new ArrayList<>();

        for (Object[] linha : resultado) {
            String mes = (String) linha[0];
            Long quantidade = (Long) linha[1];

            vwProgressaoCadastroUsuarios progressaoCadastroUsuarios = new vwProgressaoCadastroUsuarios(mes, quantidade);
            listProgressaoCadastroUsuarios.add(progressaoCadastroUsuarios);
        }

        return listProgressaoCadastroUsuarios;
    }

    @Operation(summary = "Progressão de sessões por mês", description = "Retorna a progressão da quantidade de novas sessões realizadas nos últimos 6 meses")
    public List<vwProgressaoSessoesRealizadas> progressaoRealizacaoSessoes(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_progressao_sessoes_realizadas");
        List<Object[]> resultado = query.getResultList();

        List<vwProgressaoSessoesRealizadas> listProgressaoSessoesRealizadas = new ArrayList<>();

        for (Object[] linha : resultado) {
            String mes = (String) linha[0];
            Long quantidade = (Long) linha[1];

            vwProgressaoSessoesRealizadas progressaoSessoesRealizadas = new vwProgressaoSessoesRealizadas(mes, quantidade);
            listProgressaoSessoesRealizadas.add(progressaoSessoesRealizadas);
        }

        return listProgressaoSessoesRealizadas;
    }

    @Operation(summary = "KPI Total usuários", description = "Retorna o total de usuários cadastrados e a diferença do último mês para o atual")
    public List<vwFaixaEtariaCliente> trazerKpiTotalUsuarios(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_kpi_usuarios_mes");
        List<Object[]> resultado = query.getResultList();

        List<vwFaixaEtariaCliente> faixaEtariaClienteDtos = new ArrayList<>();

        for (Object[] linha : resultado) {
            String faixa = (String) linha[0];
            Long quantidade = (Long) linha[1];

            vwFaixaEtariaCliente faixaEtariaClienteDto = new vwFaixaEtariaCliente(faixa, quantidade);
            faixaEtariaClienteDtos.add(faixaEtariaClienteDto);
        }

        return faixaEtariaClienteDtos;
    }

    @Operation(summary = "KPI Total sessões realizadas", description = "Retorna o total de sessões realizadas e a diferença do último mês para o atual")
    public List<vwFaixaEtariaCliente> trazerKpiSessoesRealizadas(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_kpi_sessoes_mes");
        List<Object[]> resultado = query.getResultList();

        List<vwFaixaEtariaCliente> faixaEtariaClienteDtos = new ArrayList<>();

        for (Object[] linha : resultado) {
            String faixa = (String) linha[0];
            Long quantidade = (Long) linha[1];

            vwFaixaEtariaCliente faixaEtariaClienteDto = new vwFaixaEtariaCliente(faixa, quantidade);
            faixaEtariaClienteDtos.add(faixaEtariaClienteDto);
        }

        return faixaEtariaClienteDtos;
    }

    @Operation(summary = "KPI Total acessos", description = "Retorna o total de acessos e a diferença do último mês para o atual")
    public List<vwFaixaEtariaCliente> trazerKpiTotalAcessos(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_kpi_acessos_mes");
        List<Object[]> resultado = query.getResultList();

        List<vwFaixaEtariaCliente> faixaEtariaClienteDtos = new ArrayList<>();

        for (Object[] linha : resultado) {
            String faixa = (String) linha[0];
            Long quantidade = (Long) linha[1];

            vwFaixaEtariaCliente faixaEtariaClienteDto = new vwFaixaEtariaCliente(faixa, quantidade);
            faixaEtariaClienteDtos.add(faixaEtariaClienteDto);
        }

        return faixaEtariaClienteDtos;
    }

    @Operation(summary = "Obter contagem de sessões que foram finalizadas ou canceladas", description = "Retorna a contagem de sessões que foram finalizadas ou canceladas.")
    public List<vwSessoesFinalizadasCanceladas> trazerContagemSessoesFinalizadasCanceladas() {
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_sessoes_realizadas_finalizadas");
        List<Object[]> resultado = query.getResultList();

        List<vwSessoesFinalizadasCanceladas> sessoesFinalizadasCanceladas = new ArrayList<>();

        for (Object[] linha : resultado) {
            String mes = (String) linha[0];
            Long convertidas = (Long) linha[1];
            Long total = (Long) linha[2];
            Long interrompidas = (Long) linha[3];

            vwSessoesFinalizadasCanceladas contagem = new vwSessoesFinalizadasCanceladas(mes, convertidas, total, interrompidas);
            sessoesFinalizadasCanceladas.add(contagem);
        }

        return sessoesFinalizadasCanceladas;
    }

}
