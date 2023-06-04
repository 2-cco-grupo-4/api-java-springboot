package com.example.picmejava.controller.dashboard;

import com.example.picmejava.service.dashboard.DashboardService;
import com.example.picmejava.service.dashboard.dto.ContagemClientesAcordoUmaSemana;
import com.example.picmejava.service.dashboard.dto.FaixaEtariaCliente;
import com.example.picmejava.service.dashboard.dto.TemaContatosCliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

@RestController
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

    @Operation(summary = "Obter faixa etária dos clientes", description = "Obtém a faixa etária dos clientes registrados")
    @GetMapping("/clientes-acordo-semana")
    public ResponseEntity<List<FaixaEtariaCliente>> trazerFaixaEtariaCliente(){
        if (dashboardService.trazerFaixaEtariaCliente().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dashboardService.trazerFaixaEtariaCliente());
    }

    @Operation(summary = "Obter contagem de contatos por tema", description = "Obtém a contagem de contatos por tema")
    @GetMapping("/contagem-tema-contato")
    public ResponseEntity<List<TemaContatosCliente>> trazerContagemTemaContato() {
        if (dashboardService.trazerFaixaEtariaCliente().isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardService.trazerContagemTemaContato());
    }

    @Operation(summary = "Obter contagem de clientes na última semana", description = "Obtém a contagem de clientes que entraram em contato na última semana")
    @GetMapping("/contagem-clientes-semana")
    public ResponseEntity<List<ContagemClientesAcordoUmaSemana>> trazerContagemClientesAcordoUmaSemana() {
        if (dashboardService.trazerContagemClientesAcordoUmaSemana().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardService.trazerContagemClientesAcordoUmaSemana());
    }

}
