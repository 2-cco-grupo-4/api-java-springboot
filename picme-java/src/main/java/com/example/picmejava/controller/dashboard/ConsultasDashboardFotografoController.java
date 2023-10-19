package com.example.picmejava.controller.dashboard;

import com.example.picmejava.service.dashboard.DashboardFotografoService;
import com.example.picmejava.service.dashboard.dto.fotografo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.persistence.EntityManager;

import java.util.List;

@RestController
@RequestMapping("/dashboard/fotografo")
@Tag(
        name = "Dashboard Fotografo Controller",
        description = "Controller responsável pelas consultas do painel fotografo"
)

public class ConsultasDashboardFotografoController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DashboardFotografoService dashboardFotografoService;

    /* 1 */
    @Operation(summary = "Obter valor médio cobrado por sessão", description = "Retorna o valor médio cobrado por sessão.")
    @GetMapping("/valor-medio-cobrado")
    public ResponseEntity<List<vwKpiValorMedioCobrado>> trazerKpiValorMedioCobrado(@RequestParam Long idFotografo) {
        if (dashboardFotografoService.trazerValorMedioCobrado(idFotografo).isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardFotografoService.trazerValorMedioCobrado(idFotografo));
    }

    @Operation(summary = "Contar sessões no mês", description = "Retorna a contagem de sessões no mês.")
    @GetMapping("/sessoes-agendadas-mes")
    public ResponseEntity<List<vwKpiSessoesAgendadasMes>> trazerKpiSessoesAgendadasMes(@RequestParam Long idFotografo) {
        if (dashboardFotografoService.trazerQuantidadeSessoesMes(idFotografo).isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardFotografoService.trazerQuantidadeSessoesMes(idFotografo));
    }

    @Operation(summary = "Contar propostas de trabalho recebidas no mês atual e anterior", description = "Retorna a contagem de propostas de trabalho recebidas no mês atual e anterior.")
    @GetMapping("/propostas-recebidas-mes")
    public ResponseEntity<List<vwKpiPropostasRecebidasMesAtual>> trazerKpiPropostasRecebidasMes(@RequestParam Long idFotografo) {
        if (dashboardFotografoService.trazerPropostasRecebidasMes(idFotografo).isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardFotografoService.trazerPropostasRecebidasMes(idFotografo));
    }

    @Operation(summary = "Obter variação do lucro mensal", description = "Retorna a variação do lucro mensal.")
    @GetMapping("/variacao-lucro-mensal")
    public ResponseEntity<List<vwKpiVariacaoLucroMensal>> trazerKpiVariacaoLucroMensal(@RequestParam Long idFotografo) {
        if (dashboardFotografoService.trazerVariacaoLucroMensal(idFotografo).isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardFotografoService.trazerVariacaoLucroMensal(idFotografo));
    }

    @Operation(summary = "Obter avaliação média por tema", description = "Retorna a avaliação média por tema.")
    @GetMapping("/avaliacao-media-tema")
    public ResponseEntity<List<vwMediaAvaliacaoPorTema>> trazerAvaliacaoMediaTema(@RequestParam Long idFotografo) {
        if (dashboardFotografoService.trazerMediaAvaliacaoTema(idFotografo).isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardFotografoService.trazerMediaAvaliacaoTema(idFotografo));
    }

    @Operation(summary = "Obter variação lucro últimos meses", description = "Retorna a variação do lucro nos últimos meses.")
    @GetMapping("/variacao-lucro-ultimos-meses")
    public ResponseEntity<List<vwVariacaoLucroUltimosMeses>> trazerVariacaoLucroUltimosMeses(@RequestParam Long idFotografo) {
        if (dashboardFotografoService.trazerVariacaoLucroUltimosMeses(idFotografo).isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardFotografoService.trazerVariacaoLucroUltimosMeses(idFotografo));
    }

    @Operation(summary = "Obter quantidade de contatos convertidos em sessões", description = "Retorna a quantidade de contatos convertidos em sessões.")
    @GetMapping("/contatos-convertidos-sessoes")
    public ResponseEntity<List<vwContatosConvertidosSessoes>> trazerContatosConvertidosSessoes(@RequestParam Long idFotografo) {
        if (dashboardFotografoService.trazerContatosConvertidosSessoes(idFotografo).isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dashboardFotografoService.trazerContatosConvertidosSessoes(idFotografo));
    }


}
