package com.example.picmejava.service.dashboard;

import com.example.picmejava.service.dashboard.dto.ContagemClientesAcordoUmaSemana;
import com.example.picmejava.service.dashboard.dto.FaixaEtariaCliente;
import com.example.picmejava.service.dashboard.dto.TemaContatosCliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

@Service
@Tag(name = "Dashboard", description = "Endpoints para informações do painel de controle")
public class DashboardService {

    @Autowired
    private EntityManager entityManager;

    private <T> List<T> executeNativeQuery(String sql, Class<T> resultClass) {
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> resultado = query.getResultList();
        List<T> resultList = new ArrayList<>();

        for (Object[] linha : resultado) {
            resultList.add(resultClass.cast(newInstance(resultClass, linha)));
        }

        return resultList;
    }

    private <T> T newInstance(Class<T> clazz, Object[] args) {
        try {
            return clazz.getConstructor(Object[].class).newInstance((Object) args);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar uma instância da classe " + clazz.getName(), e);
        }
    }

    @Operation(summary = "Obter faixa etária dos clientes", description = "Retorna a contagem de clientes por faixa etária.")
    @GetMapping("/faixa-etaria-cliente")
    public List<FaixaEtariaCliente> trazerFaixaEtariaCliente() {
        String sql = "SELECT * FROM vw_faixa_etaria_cliente";
        return executeNativeQuery(sql, FaixaEtariaCliente.class);
    }

    @Operation(summary = "Obter faixa etária dos clientes por tema", description = "Retorna a contagem de clientes divididos pela sua faixa etária e tema de evento")
    @GetMapping("/faixa-etaria-cliente-tema/{tema}")
    public List<FaixaEtariaCliente> trazerFaixaEtariaClienteTema(@PathVariable String tema) {
        String sql = "CALL proc_faixa_etaria_cliente_tema(:tema)";
        return executeNativeQuery(sql, FaixaEtariaCliente.class);
    }

    @Operation(summary = "Obter contagem de contatos por tema", description = "Retorna a contagem de contatos por tema.")
    @GetMapping("/contagem-tema-contato")
    public List<TemaContatosCliente> trazerContagemTemaContato() {
        String sql = "SELECT * FROM vw_contagem_tema_contato";
        return executeNativeQuery(sql, TemaContatosCliente.class);
    }

    @Operation(summary = "Obter contagem de clientes que fecharam acordo em acordo em uma semana", description = "Retorna a contagem de clientes em acordo em uma semana.")
    @GetMapping("/contagem-clientes-acordo-uma-semana")
    public List<ContagemClientesAcordoUmaSemana> trazerContagemClientesAcordoUmaSemana() {
        String sql = "SELECT * FROM vw_clientes_acordo_1semana";
        return executeNativeQuery(sql, ContagemClientesAcordoUmaSemana.class);
    }

    @Operation(summary = "Obter total de clientes e fotógrafos", description = "Retorna a contagem total de clientes e de fotógrafos.")
    @GetMapping("/total-clientes-fotografos")
    public List<FaixaEtariaCliente> totalClientesFotografos() {
        String sql = "SELECT * FROM vw_total_clientes_fotografos";
        return executeNativeQuery(sql, FaixaEtariaCliente.class);
    }

    @Operation(summary = "Progressão de usuários por mês", description = "Retorna a progressão da quantidade de novos usuários cadastrados nos últimos 6 meses")
    @GetMapping("/progressao-usuarios-mes")
    public List<FaixaEtariaCliente> progressaoUsuariosMes() {
        String sql = "SELECT * FROM vw_progressao_cadastro_usuarios";
        return executeNativeQuery(sql, FaixaEtariaCliente.class);
    }

    @Operation(summary = "Progressão de sessões por mês", description = "Retorna a progressão da quantidade de novas sessões realizadas nos últimos 6 meses")
    @GetMapping("/progressao-realizacao-sessoes")
    public List<FaixaEtariaCliente> progressaoRealizacaoSessoes() {
        String sql = "SELECT * FROM vw_progressao_sessoes_realizadas";
        return executeNativeQuery(sql, FaixaEtariaCliente.class);
    }

    @Operation(summary = "KPI Total usuários", description = "Retorna o total de usuários cadastrados e a diferença do último mês para o atual")
    @GetMapping("/kpi-total-usuarios")
    public List<FaixaEtariaCliente> trazerKpiTotalUsuarios() {
        String sql = "SELECT * FROM vw_kpi_usuarios_mes";
        return executeNativeQuery(sql, FaixaEtariaCliente.class);
    }

    @Operation(summary = "KPI Total sessões realizadas", description = "Retorna o total de sessões realizadas e a diferença do último mês para o atual")
    @GetMapping("/kpi-sessoes-realizadas")
    public List<FaixaEtariaCliente> trazerKpiSessoesRealizadas() {
        String sql = "SELECT * FROM vw_total_sessoes_realizadas";
        return executeNativeQuery(sql, FaixaEtariaCliente.class);
    }

    @Operation(summary = "KPI Total acessos", description = "Retorna o total de acessos e a diferença do último mês para o atual")
    @GetMapping("/kpi-total-acessos")
    public List<FaixaEtariaCliente> trazerKpiTotalAcessos() {
        String sql = "SELECT * FROM vw_acessos_mes";
        return executeNativeQuery(sql, FaixaEtariaCliente.class);
    }

    @Operation(summary = "Obter contagem de sessões que foram finalizadas ou canceladas", description = "Retorna a contagem de sessões que foram finalizadas ou canceladas.")
    @GetMapping("/contagem-sessoes-finalizadas-canceladas")
    public List<ContagemClientesAcordoUmaSemana> trazerContagemSessoesFinalizadasCanceladas() {
        String sql = "SELECT * FROM vw_total_sessoes_finalizadas_canceladas";
        return executeNativeQuery(sql, ContagemClientesAcordoUmaSemana.class);
    }


}
