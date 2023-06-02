package com.example.picmejava.service.dashboard;

import com.example.picmejava.service.dashboard.dto.ContagemClientesAcordoUmaSemana;
import com.example.picmejava.service.dashboard.dto.FaixaEtariaCliente;
import com.example.picmejava.service.dashboard.dto.TemaContatosCliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private EntityManager entityManager;

    public List<FaixaEtariaCliente> trazerFaixaEtariaCliente(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_faixa_etaria_cliente");
        List<Object[]> resultado = query.getResultList();

        List<FaixaEtariaCliente> faixaEtariaClientes = new ArrayList<>();

        for (Object[] linha : resultado) {
            String faixa = (String) linha[0];
            Long quantidade = (Long) linha[1];

            FaixaEtariaCliente faixaEtariaCliente = new FaixaEtariaCliente(faixa, quantidade);
            faixaEtariaClientes.add(faixaEtariaCliente);
        }

        return faixaEtariaClientes;
    }

    public List<TemaContatosCliente> trazerContagemTemaContato(){
        Query query = entityManager.createNativeQuery("SELECT tema, contatos FROM vw_contagem_tema_contato");
        List<Object[]> resultado = query.getResultList();

        List<TemaContatosCliente> temaContatosClientes = new ArrayList<>();

        for (Object[] linha : resultado) {
            String tema = (String) linha[0];
            Long contatos = (Long) linha[1];


            TemaContatosCliente temaContatosCliente = new TemaContatosCliente(tema, contatos);
            temaContatosClientes.add(temaContatosCliente);
        }
        return temaContatosClientes;
    }

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
