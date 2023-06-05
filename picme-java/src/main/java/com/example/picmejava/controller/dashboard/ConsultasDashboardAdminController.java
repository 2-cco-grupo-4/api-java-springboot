package com.example.picmejava.controller.dashboard;

import com.example.picmejava.service.dashboard.DashboardService;
import com.example.picmejava.service.dashboard.dto.ContagemClientesAcordoUmaSemana;
import com.example.picmejava.service.dashboard.dto.FaixaEtariaCliente;
import com.example.picmejava.service.dashboard.dto.TemaContatosCliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/admin")
@Tag(
        name = "Dashboard Admin Controller",
        description = "Controller responsável pelas consultas do painel administrativo"
)
public class ConsultasDashboardAdminController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DashboardService dashboardService;

    /* 1 */
    @Operation(summary = "Obter faixa etária dos clientes", description = "Obtém a faixa etária dos clientes registrados")
    @GetMapping("/faixa-etaria-clientes")
    public ResponseEntity<List<FaixaEtariaCliente>> trazerFaixaEtariaCliente(){
        if (dashboardService.trazerFaixaEtariaCliente().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dashboardService.trazerFaixaEtariaCliente());
    }

    /* 3 */
    @Operation(summary = "Obter contagem de contatos por tema", description = "Obtém a contagem de contatos por tema")
    @GetMapping("/contagem-tema-contato")
    public ResponseEntity<List<TemaContatosCliente>> trazerContagemTemaContato() {
        if (dashboardService.trazerFaixaEtariaCliente().isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardService.trazerContagemTemaContato());
    }

    /* 2 */
    @Operation(summary = "Obter faixa etária dos clientes por tema", description = "Retorna a contagem de clientes divididos pela sua faixa etária e tema de evento")
    @GetMapping("/faixa-etaria-clientes-tema/{tema}")
    public ResponseEntity<List<FaixaEtariaCliente>> trazerFaixaEtariaClienteTema(@PathVariable String tema) {
        if (dashboardService.trazerFaixaEtariaClienteTema(tema).isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardService.trazerFaixaEtariaClienteTema(tema));
    }



    /* 4 */
    @Operation(summary = "Obter contagem de clientes que fecharam acordo em acordo em uma semana", description = "Retorna a contagem de clientes em acordo em uma semana.")
    @GetMapping("/contagem-clientes-semana")
    public ResponseEntity<List<ContagemClientesAcordoUmaSemana>> trazerContagemClientesAcordoUmaSemana() {
        if (dashboardService.trazerContagemClientesAcordoUmaSemana().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardService.trazerContagemClientesAcordoUmaSemana());
    }

    /* 5 */
    @Operation(summary = "Obter total de clientes e fotógrafos", description = "Retorna a contagem total de clientes e de fotógrafos.")
    @GetMapping("/total-clientes-fotografos")
    public ResponseEntity<List<FaixaEtariaCliente>> totalClientesFotografos() {
        if (dashboardService.totalClientesFotografos().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardService.totalClientesFotografos());
    }

    /* 6 */
    @Operation(summary = "Progressão de usuários por mês", description = "Retorna a progressão da quantidade de novos usuários cadastrados nos últimos 6 meses")
    @GetMapping("/progressao-usuarios-mes")
    public ResponseEntity<List<FaixaEtariaCliente>> progressaoUsuariosMes() {
        if (dashboardService.progressaoUsuariosMes().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardService.progressaoUsuariosMes());
    }

    /* 7 */
    @Operation(summary = "Progressão de sessões por mês", description = "Retorna a progressão da quantidade de novas sessões realizadas nos últimos 6 meses")
    @GetMapping("/progressao-relizacao-sessoes")
    public ResponseEntity<List<FaixaEtariaCliente>> progressaoRealizacaoSessoes() {
        if (dashboardService.progressaoRealizacaoSessoes().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardService.progressaoRealizacaoSessoes());
    }

    /* 8 */
    @Operation(summary = "KPI Total usuários", description = "Retorna o total de usuários cadastrados e a diferença do último mês para o atual")
    @GetMapping("/kpi-total-usuarios")
    public ResponseEntity<List<FaixaEtariaCliente>> trazerKpiTotalUsuarios() {
        if (dashboardService.trazerKpiTotalUsuarios().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardService.trazerKpiTotalUsuarios());
    }

    /* 9 */
    @Operation(summary = "KPI Total sessões realizadas", description = "Retorna o total de sessões realizadas e a diferença do último mês para o atual")
    @GetMapping("/kpi-sessoes-realizadas")
    public ResponseEntity<List<FaixaEtariaCliente>> trazerKpiSessoesRealizadas() {
        if (dashboardService.trazerKpiSessoesRealizadas().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardService.trazerKpiSessoesRealizadas());
    }

    /* 10 */
    @Operation(summary = "KPI Total acessos", description = "Retorna o total de acessos e a diferença do último mês para o atual")
    @GetMapping("/kpi-total-acessos")
    public ResponseEntity<List<FaixaEtariaCliente>> trazerKpiTotalAcessos() {
        if (dashboardService.trazerKpiTotalAcessos().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardService.trazerKpiTotalAcessos());
    }

    /* 11 */
    @Operation(summary = "Obter contagem de sessões que foram finalizadas ou canceladas", description = "Retorna a contagem de sessões que foram finalizadas ou canceladas.")
    @GetMapping("/sessoes-finalizadas-canceladas")
    public ResponseEntity<List<ContagemClientesAcordoUmaSemana>> trazerContagemSessoesFinalizadasCanceladas() {
        if (dashboardService.trazerContagemSessoesFinalizadasCanceladas().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardService.trazerContagemSessoesFinalizadasCanceladas());
    }

}
