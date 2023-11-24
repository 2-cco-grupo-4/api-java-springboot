package com.example.picmejava.service.pesquisa;


import com.example.picmejava.service.pesquisa.dto.vwPesquisaTermo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Tag(name = "Pesquisa", description = "Pesquisa de termos")
public class PesquisaService {

    @Autowired
    private EntityManager entityManager;

    @Operation(summary = "Pesquisar termo", description = "Pesquisa termo por nome")
    public List<vwPesquisaTermo> pesquisarTermo(String termo){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_pesquisa_termo WHERE termo LIKE :termo");
        query.setParameter("termo", "%" + termo + "%");
        List<Object[]> resultado = query.getResultList();

        List<vwPesquisaTermo> listaPesquisaTermo = new ArrayList<>();

        for (Object[] linha : resultado) {
            String termoPesquisado = (String) linha[0];
            String tipo = (String) linha[1];

            vwPesquisaTermo pesquisaTermo = new vwPesquisaTermo(termoPesquisado, tipo);
            listaPesquisaTermo.add(pesquisaTermo);
        }

        return listaPesquisaTermo;
    }

}
