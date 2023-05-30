package com.example.picmejava.controller.dashboard;

import com.example.picmejava.repository.ClienteRepository;
import com.example.picmejava.repository.EventoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ConsultasDashboardAdmin {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/clientes-acordo-semana")
    public Object trazerClientesComAcordoEmUmaSemana(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_clientes_acordo_1semana");
        return query.getSingleResult();
    }

    @GetMapping("/contagem-tema-contato")
    public List<FaixaEtariaClienteDto> trazerContagemTemaContato(){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_contagem_tema_contato");
        List<FaixaEtariaClienteDto> faixaEtariaClienteDtos = query.getResultList();

        return faixaEtariaClienteDtos;
    }


}
