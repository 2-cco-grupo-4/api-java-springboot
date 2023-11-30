package com.example.picmejava.repository;

import com.example.picmejava.model.Endereco;
import com.example.picmejava.service.endereco.dto.RetornoEnderecoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Endereco findBySessaoId(Long id);

}
