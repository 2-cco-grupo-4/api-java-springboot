package com.example.picmejava.repository;

import com.example.picmejava.model.Pagamento;
import com.example.picmejava.service.evento.dto.RetornoPagamentoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    Pagamento findBySessaoId(Long id);

}
