package com.example.picmejava.controller;

import com.example.picmejava.service.tema.TemaService;
import com.example.picmejava.service.tema.dto.CadastroTemaDto;
import com.example.picmejava.service.tema.dto.PerfilTemaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class TemaControllerTest {


    @Mock
    private TemaService temaService;

    @InjectMocks
    private TemaController temaController;

    @Test
    @DisplayName("Deve cadastrar um novo tema")
    void cadastrarTema() {
        CadastroTemaDto novoTema = new CadastroTemaDto();
        PerfilTemaDTO perfilTema = new PerfilTemaDTO();

        when(temaService.cadastrar(novoTema)).thenReturn(perfilTema);

        ResponseEntity<PerfilTemaDTO> response = temaController.cadastrar(novoTema);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(perfilTema, response.getBody());
    }

    @Test
    @DisplayName("Deve listar todos os temas")
    void listarTemas() {
        List<PerfilTemaDTO> temas = new ArrayList<>();
        temas.add(new PerfilTemaDTO());

        when(temaService.listar()).thenReturn(temas);

        ResponseEntity<List<PerfilTemaDTO>> response = temaController.listar();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(temas, response.getBody());
    }
}
