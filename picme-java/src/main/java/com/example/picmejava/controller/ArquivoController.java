package com.example.picmejava.controller;

import com.example.picmejava.service.arquivo.ArquivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController

@RequestMapping("/arquivo")
@Tag(name = "Arquivo", description = "Endpoints para exportação e importação de arquivos")
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @Operation(summary = "Exportar lista de preferências de temas dos usuários", description = "Download de arquivo TXT com a lista de preferências de temas dos usuários")
    @GetMapping("/preferencias-temas")
    public ResponseEntity<byte[]> downloadPlainTextFile() throws IOException {

        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
        String filename = String.format("listaPreferenciasTemas_%s.txt", LocalDate.now().format(dataFormatter));

        System.out.println(filename);

        arquivoService.listarPreferenciasTemas(filename);

        byte[] fileContent = Files.readAllBytes(Paths.get(filename));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.TEXT_PLAIN);
        httpHeaders.setContentDispositionFormData("attachment", filename);

        File archive = new File(filename);

        if(archive.exists()){
            if(archive.delete()){
                System.out.println("Arquivo deletado com sucesso!");
            }else{
                System.out.println("Falha ao deletar arquivo!");
            }
        }

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(fileContent);

    }

    @Operation(summary = "Importar lista de temas e tags externa", description = "Importar lista de temas e tags externa")
    @PostMapping("/importar-temas-tags")
    public ResponseEntity<String> importarTemasTags(@RequestParam("file") MultipartFile file) {

        arquivoService.importPlainTextFile(file);

        return ResponseEntity.ok("Importação realizada com sucesso!");

    }

}
