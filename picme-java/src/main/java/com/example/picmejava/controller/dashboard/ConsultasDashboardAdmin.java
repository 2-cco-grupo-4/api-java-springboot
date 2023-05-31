package com.example.picmejava.controller.dashboard;

import com.example.picmejava.repository.ClienteRepository;
import com.example.picmejava.repository.EventoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class ConsultasDashboardAdmin {

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/clientes-acordo-semana")
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

    @GetMapping("/contagem-tema-contato")
    public List<TemaContatosClienteDto> trazerContagemTemaContato() {
        Query query = entityManager.createNativeQuery("SELECT tema, contatos FROM vw_contagem_tema_contato");
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
            Long clienteContato = (Long) linha[0];
            Long totalCliente = (Long) linha[1];

            ContagemClientesAcordoUmaSemana contagem = new ContagemClientesAcordoUmaSemana(clienteContato, totalCliente);
            listaContagem.add(contagem);
        }

        return listaContagem;
    }

}
