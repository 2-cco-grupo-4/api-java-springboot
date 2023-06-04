package com.example.picmejava.service.dashboard;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class DashboardServiceTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @InjectMocks
    private DashboardService dashboardService;

//    @Test
//    public void testTrazerFaixaEtariaCliente() {
//        Object[] linha1 = {"Faixa 1", 10L};
//        List<Object[]> resultado = new ArrayList<>();
//        resultado.add(linha1);
//
//        when(entityManager.createNativeQuery(Mockito.anyString())).thenReturn(query);
//        when(query.getResultList()).thenReturn(resultado);
//
//        List<FaixaEtariaCliente> faixaEtariaClientes = dashboardService.trazerFaixaEtariaCliente();
//
//        assertEquals(1, faixaEtariaClientes.size());
//        FaixaEtariaCliente faixaEtariaCliente = faixaEtariaClientes.get(0);
//        assertEquals("Faixa 1", faixaEtariaCliente.getFaixa());
//        assertEquals(10L, faixaEtariaCliente.getQuantidade());
//    }


}