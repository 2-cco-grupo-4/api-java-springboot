package com.example.picmejava.controller.dashboard;

import com.example.picmejava.repository.ClienteRepository;
import com.example.picmejava.repository.EventoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:3000")
public class ConsultasDashboardAdmin {

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/faixa-etaria-clientes")
    public List<FaixaEtariaClienteDto> trazerFaixaEtariaCliente(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_faixa_etaria_cliente");
        List<Object[]> resultado = query.getResultList();

        List<FaixaEtariaClienteDto> faixaEtariaClienteDtos = new ArrayList<>();

        for (Object[] linha : resultado) {
            String faixa = (String) linha[0];
            Long quantidade = (Long) linha[1];

            FaixaEtariaClienteDto faixaEtariaClienteDto = new FaixaEtariaClienteDto(faixa, quantidade);
            faixaEtariaClienteDtos.add(faixaEtariaClienteDto);
        }

        return faixaEtariaClienteDtos;
    }

    @GetMapping("/faixa-etaria-clientes-tema/{tema}")
    public List<FaixaEtariaClienteDto> trazerFaixaEtariaClienteTema(@PathVariable String tema){
        Query query = entityManager.createNativeQuery("CALL proc_faixa_etaria_cliente_tema(:tema)");
        query.setParameter("tema", tema);
        List<Object[]> resultado = query.getResultList();

        List<FaixaEtariaClienteDto> faixaEtariaClienteDtos = new ArrayList<>();

        for (Object[] linha : resultado) {
            String faixa = (String) linha[0];
            Long quantidade = (Long) linha[1];

            FaixaEtariaClienteDto faixaEtariaClienteDto = new FaixaEtariaClienteDto(faixa, quantidade);
            faixaEtariaClienteDtos.add(faixaEtariaClienteDto);
        }

        return faixaEtariaClienteDtos;
    }

    @GetMapping("/contagem-tema-contato")
    public List<TemaContatosClienteDto> trazerContagemTemaContato() {
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_contagem_tema_contato");
        List<Object[]> resultado = query.getResultList();

        List<TemaContatosClienteDto> temaContatosClienteDtos = new ArrayList<>();

        for (Object[] linha : resultado) {
            String tema = (String) linha[0];
            Long contatos = (Long) linha[1];


            TemaContatosClienteDto temaContatosClienteDto = new TemaContatosClienteDto(tema, contatos);
            temaContatosClienteDtos.add(temaContatosClienteDto);
        }

        return temaContatosClienteDtos;
    }

    @GetMapping("/contagem-clientes-semana")
    public List<ContagemClientesAcordoUmaSemana> trazerContagemClientesAcordoUmaSemana() {
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_clientes_acordo_1semana");
        List<Object[]> resultado = query.getResultList();

        List<ContagemClientesAcordoUmaSemana> listaContagem = new ArrayList<>();

        for (Object[] linha : resultado) {
            String label = (String) linha[0];
            Long quantidade = (Long) linha[1];

            ContagemClientesAcordoUmaSemana contagem = new ContagemClientesAcordoUmaSemana(label, quantidade);
            listaContagem.add(contagem);
        }

        return listaContagem;
    }

    @GetMapping("/total-clientes-fotografos")
    public List<FaixaEtariaClienteDto> totalClientesFotografos(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_total_clientes_fotografos");
        List<Object[]> resultado = query.getResultList();

        List<FaixaEtariaClienteDto> faixaEtariaClienteDtos = new ArrayList<>();

        for (Object[] linha : resultado) {
            String faixa = (String) linha[0];
            Long quantidade = (Long) linha[1];

            FaixaEtariaClienteDto faixaEtariaClienteDto = new FaixaEtariaClienteDto(faixa, quantidade);
            faixaEtariaClienteDtos.add(faixaEtariaClienteDto);
        }

        return faixaEtariaClienteDtos;
    }

    @GetMapping("/progressao-usuarios-mes")
    public List<FaixaEtariaClienteDto> progressaoUsuariosMes(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_progressao_cadastro_usuarios");
        List<Object[]> resultado = query.getResultList();

        List<FaixaEtariaClienteDto> faixaEtariaClienteDtos = new ArrayList<>();

        for (Object[] linha : resultado) {
            String faixa = (String) linha[0];
            Long quantidade = (Long) linha[1];

            FaixaEtariaClienteDto faixaEtariaClienteDto = new FaixaEtariaClienteDto(faixa, quantidade);
            faixaEtariaClienteDtos.add(faixaEtariaClienteDto);
        }

        return faixaEtariaClienteDtos;
    }

    @GetMapping("/progressao-relizacao-sessoes")
    public List<FaixaEtariaClienteDto> progressaoRealizacaoSessoes(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_progressao_sessoes_realizadas");
        List<Object[]> resultado = query.getResultList();

        List<FaixaEtariaClienteDto> faixaEtariaClienteDtos = new ArrayList<>();

        for (Object[] linha : resultado) {
            String faixa = (String) linha[0];
            Long quantidade = (Long) linha[1];

            FaixaEtariaClienteDto faixaEtariaClienteDto = new FaixaEtariaClienteDto(faixa, quantidade);
            faixaEtariaClienteDtos.add(faixaEtariaClienteDto);
        }

        return faixaEtariaClienteDtos;
    }

    @GetMapping("/kpi-total-usuarios")
    public List<FaixaEtariaClienteDto> trazerKpiTotalUsuarios(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_kpi_usuarios_mes");
        List<Object[]> resultado = query.getResultList();

        List<FaixaEtariaClienteDto> faixaEtariaClienteDtos = new ArrayList<>();

        for (Object[] linha : resultado) {
            String faixa = (String) linha[0];
            Long quantidade = (Long) linha[1];

            FaixaEtariaClienteDto faixaEtariaClienteDto = new FaixaEtariaClienteDto(faixa, quantidade);
            faixaEtariaClienteDtos.add(faixaEtariaClienteDto);
        }

        return faixaEtariaClienteDtos;
    }

    @GetMapping("/kpi-sessoes-realizadas")
    public List<FaixaEtariaClienteDto> trazerKpiSessoesRealizadas(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_total_sessoes_realizadas");
        List<Object[]> resultado = query.getResultList();

        List<FaixaEtariaClienteDto> faixaEtariaClienteDtos = new ArrayList<>();

        for (Object[] linha : resultado) {
            String faixa = (String) linha[0];
            Long quantidade = (Long) linha[1];

            FaixaEtariaClienteDto faixaEtariaClienteDto = new FaixaEtariaClienteDto(faixa, quantidade);
            faixaEtariaClienteDtos.add(faixaEtariaClienteDto);
        }

        return faixaEtariaClienteDtos;
    }

    @GetMapping("/kpi-total-acessos")
    public List<FaixaEtariaClienteDto> trazerKpiTotalAcessos(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_acessos_mes");
        List<Object[]> resultado = query.getResultList();

        List<FaixaEtariaClienteDto> faixaEtariaClienteDtos = new ArrayList<>();

        for (Object[] linha : resultado) {
            String faixa = (String) linha[0];
            Long quantidade = (Long) linha[1];

            FaixaEtariaClienteDto faixaEtariaClienteDto = new FaixaEtariaClienteDto(faixa, quantidade);
            faixaEtariaClienteDtos.add(faixaEtariaClienteDto);
        }

        return faixaEtariaClienteDtos;
    }

    @GetMapping("/sessoes-finalizadas-canceladas")
    public List<ContagemClientesAcordoUmaSemana> trazerContagemSessoesFinalizadasCanceladas() {
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_total_sessoes_finalizadas_canceladas");
        List<Object[]> resultado = query.getResultList();

        List<ContagemClientesAcordoUmaSemana> listaContagem = new ArrayList<>();

        for (Object[] linha : resultado) {
            String label = (String) linha[0];
            Long quantidade = (Long) linha[1];

            ContagemClientesAcordoUmaSemana contagem = new ContagemClientesAcordoUmaSemana(label, quantidade);
            listaContagem.add(contagem);
        }

        return listaContagem;
    }

}
