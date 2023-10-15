package com.example.picmejava.controller.dashboard;

import com.example.picmejava.service.dashboard.DashboardAdminService;
import com.example.picmejava.service.dashboard.dto.admin.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/dashboard/admin")
@Tag(
        name = "Dashboard Admin Controller",
        description = "Controller responsável pelas consultas do painel administrativo"
)
public class ConsultasDashboardAdminController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DashboardAdminService dashboardAdminService;

    /* 1 */
    @Operation(summary = "Obter faixa etária dos clientes", description = "Obtém a faixa etária dos clientes registrados")
    @GetMapping("/faixa-etaria-clientes")
    public ResponseEntity<List<vwFaixaEtariaCliente>> trazerFaixaEtariaCliente(){
        if (dashboardAdminService.trazerFaixaEtariaCliente().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dashboardAdminService.trazerFaixaEtariaCliente());
    }

    /* 3 */
    @Operation(summary = "Obter contagem de contatos por tema", description = "Obtém a contagem de contatos por tema")
    @GetMapping("/contagem-tema-contato")
    public ResponseEntity<List<vwTemaCountSessoes>> trazerContagemTemaContato() {
        if (dashboardAdminService.trazerContagemTemaContato().isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardAdminService.trazerContagemTemaContato());
    }

    /* 2 */
//    @Operation(summary = "Obter faixa etária dos clientes por tema", description = "Retorna a contagem de clientes divididos pela sua faixa etária e tema de evento")
//    @GetMapping("/faixa-etaria-clientes-tema/{tema}")
//    public ResponseEntity<List<vwFaixaEtariaCliente>> trazerFaixaEtariaClienteTema(@PathVariable String tema) {
//        if (dashboardService.trazerFaixaEtariaClienteTema(tema).isEmpty()){
//            ResponseEntity.noContent().build();
//        }
//
//        return ResponseEntity.ok(dashboardService.trazerFaixaEtariaClienteTema(tema));
//    }



    /* 4 - OK! */
    @Operation(summary = "Obter contagem de clientes que fecharam acordo em acordo em uma semana", description = "Retorna a contagem de clientes em acordo em uma semana.")
    @GetMapping("/contagem-clientes-semana")
    public ResponseEntity<List<vwClientesImediatosMes>> trazerContagemClientesAcordoUmaSemana() {
        if (dashboardAdminService.trazerContagemClientesAcordoUmaSemana().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardAdminService.trazerContagemClientesAcordoUmaSemana());
    }

    /* 5 */
    @Operation(summary = "Obter total de clientes e fotógrafos", description = "Retorna a contagem total de clientes e de fotógrafos nos últimos 4 meses.")
    @GetMapping("/total-clientes-fotografos")
    public ResponseEntity<List<vwClientesFotografos>> totalClientesFotografos() {
        if (dashboardAdminService.totalClientesFotografos().isEmpty()){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dashboardAdminService.totalClientesFotografos());
    }

    /* 6 */
    @Operation(summary = "Progressão de usuários por mês", description = "Retorna a progressão da quantidade de novos usuários cadastrados nos últimos 6 meses")
    @GetMapping("/progressao-usuarios-mes")
    public ResponseEntity<List<vwProgressaoCadastroUsuarios>> progressaoUsuariosMes() {
        if (dashboardAdminService.progressaoUsuariosMes().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardAdminService.progressaoUsuariosMes());
    }

    /* 7 */
    @Operation(summary = "Progressão de sessões por mês", description = "Retorna a progressão da quantidade de novas sessões realizadas nos últimos 6 meses")
    @GetMapping("/progressao-relizacao-sessoes")
    public ResponseEntity<List<vwProgressaoSessoesRealizadas>> progressaoRealizacaoSessoes() {
        if (dashboardAdminService.progressaoRealizacaoSessoes().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardAdminService.progressaoRealizacaoSessoes());
    }

    /* 8 */
    @Operation(summary = "KPI Total usuários", description = "Retorna o total de usuários cadastrados e a diferença do último mês para o atual")
    @GetMapping("/kpi-total-usuarios")
    public ResponseEntity<List<vwFaixaEtariaCliente>> trazerKpiTotalUsuarios() {
        if (dashboardAdminService.trazerKpiTotalUsuarios().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardAdminService.trazerKpiTotalUsuarios());
    }

    /* 9 */
    @Operation(summary = "KPI Total sessões realizadas", description = "Retorna o total de sessões realizadas e a diferença do último mês para o atual")
    @GetMapping("/kpi-sessoes-realizadas")
    public ResponseEntity<List<vwFaixaEtariaCliente>> trazerKpiSessoesRealizadas() {
        if (dashboardAdminService.trazerKpiSessoesRealizadas().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardAdminService.trazerKpiSessoesRealizadas());
    }

    /* 10 */
    @Operation(summary = "KPI Total acessos", description = "Retorna o total de acessos e a diferença do último mês para o atual")
    @GetMapping("/kpi-total-acessos")
    public ResponseEntity<List<vwFaixaEtariaCliente>> trazerKpiTotalAcessos() {
        if (dashboardAdminService.trazerKpiTotalAcessos().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardAdminService.trazerKpiTotalAcessos());
    }

    /* 11 */
    @Operation(summary = "Obter contagem de sessões que foram finalizadas ou canceladas", description = "Retorna a contagem de sessões que foram finalizadas ou canceladas.")
    @GetMapping("/sessoes-finalizadas-canceladas")
    public ResponseEntity<List<vwSessoesFinalizadasCanceladas>> trazerContagemSessoesFinalizadasCanceladas() {
        if (dashboardAdminService.trazerContagemSessoesFinalizadasCanceladas().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardAdminService.trazerContagemSessoesFinalizadasCanceladas());
    }

}
