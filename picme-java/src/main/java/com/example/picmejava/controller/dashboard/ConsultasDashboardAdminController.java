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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class ConsultasDashboardAdminController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/clientes-acordo-semana")
    public ResponseEntity<List<FaixaEtariaCliente>> trazerFaixaEtariaCliente(){
        if (dashboardService.trazerFaixaEtariaCliente().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dashboardService.trazerFaixaEtariaCliente());
    }

    @GetMapping("/contagem-tema-contato")
    public ResponseEntity<List<TemaContatosCliente>> trazerContagemTemaContato() {
        if (dashboardService.trazerFaixaEtariaCliente().isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardService.trazerContagemTemaContato());
    }

    @GetMapping("/contagem-clientes-semana")
    public ResponseEntity<List<ContagemClientesAcordoUmaSemana>> trazerContagemClientesAcordoUmaSemana() {
        if (dashboardService.trazerContagemClientesAcordoUmaSemana().isEmpty()){
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardService.trazerContagemClientesAcordoUmaSemana());
    }

}
