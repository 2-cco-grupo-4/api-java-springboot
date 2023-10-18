package com.example.picmejava.service.arquivo;

import com.example.picmejava.model.Tema;
import com.example.picmejava.repository.TagRepository;
import com.example.picmejava.repository.TemaRepository;
import com.example.picmejava.service.arquivo.dto.vwExportUsuarioPreferenciasTemas;
import com.example.picmejava.service.dashboard.dto.admin.vwFaixaEtariaCliente;
import com.example.picmejava.service.tag.TagService;
import com.example.picmejava.service.tag.dto.CadastroTagDTO;
import com.example.picmejava.service.tema.TemaService;
import com.example.picmejava.service.tema.dto.CadastroTemaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Tag(name = "Arquivo Service", description = "Endpoints para tramtamento de importação e exportação de arquivos plain text (TXT) e CSV")
public class ArquivoService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private static TemaService temaService;
    @Autowired
    private static TagService tagService;

    public ArquivoService(TemaService temaService, TagService tagService) {
        this.tagService = tagService;
        this.temaService = temaService;
    }

    @Operation(summary = "Listar preferências de temas dos usuários", description = "Retorna a lista de preferências de temas dos usuários")
    public void listarPreferenciasTemas(String filename){
        Query query = entityManager.createNativeQuery("SELECT * FROM vw_export_usuario_preferencias_temas");
        List<Object[]> resultado = query.getResultList();

        List<vwExportUsuarioPreferenciasTemas> listaPreferenciasTemas = new ArrayList<>();

        for (Object[] linha : resultado) {
            Long idUsuario = (Long) linha[0];
            String nomeUsuario = (String) linha[1];
            java.sql.Date sqlDataNascimento = (java.sql.Date) linha[2];
            LocalDate dataNascimento = sqlDataNascimento.toLocalDate();
            String celular = (String) linha[3];
            java.sql.Timestamp sqlDataCadastro = (java.sql.Timestamp) linha[4];
            LocalDateTime dataCadastro = sqlDataCadastro.toLocalDateTime();
            Byte byteValue = (Byte) linha[5];
            int tipoUsuario = byteValue.intValue();
            String cidadePreferencia = (String) linha[6];
            String estadoPreferencia = (String) linha[7];
            Long idTema = (Long) linha[8];
            String nomeTema = (String) linha[9];


            vwExportUsuarioPreferenciasTemas exportUsuarioPreferenciasTemas = new vwExportUsuarioPreferenciasTemas(idUsuario, nomeUsuario, dataNascimento, celular, dataCadastro, tipoUsuario, cidadePreferencia, estadoPreferencia, idTema, nomeTema);
            listaPreferenciasTemas.add(exportUsuarioPreferenciasTemas);
        }

        exportPlainTextFile(listaPreferenciasTemas, filename);
    }

    @Operation(summary = "Gerar arquivo TXT com lista de preferências de temas dos usuários", description = "Gera arquivo TXT com a lista de preferências de temas dos usuários")
    public static void exportPlainTextFile(List<vwExportUsuarioPreferenciasTemas> listExport, String filename) {

        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
        DateTimeFormatter dataHoraFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy HH:mm:ss");

        int sumLinesData = 0;

        String header = "0TEMAUSER" + LocalDate.now() + "001";



        writeLine(filename, header);

        for (vwExportUsuarioPreferenciasTemas vwExportUsuarioPreferenciasTemas : listExport) {
            String dataLine = "1";
            dataLine += String.format("%08d%-120.120s%-10.10s%-20.20s%-19.19s%01d%-150.150s%-150.150s%08d%-50.50s",
                    vwExportUsuarioPreferenciasTemas.getIdUsuario(),
                    vwExportUsuarioPreferenciasTemas.getNomeUser(),
                    vwExportUsuarioPreferenciasTemas.getDataNascimento().format(dataFormatter),
                    vwExportUsuarioPreferenciasTemas.getCelular(),
                    vwExportUsuarioPreferenciasTemas.getDataCadastro().format(dataHoraFormatter),
                    vwExportUsuarioPreferenciasTemas.getTipoUsuario(),
                    vwExportUsuarioPreferenciasTemas.getCidadePreferencia(),
                    vwExportUsuarioPreferenciasTemas.getEstadoPreferencia(),
                    vwExportUsuarioPreferenciasTemas.getIdTema(),
                    vwExportUsuarioPreferenciasTemas.getNomeTema()
            );

            writeLine(filename, dataLine);
            sumLinesData++;
        }

        String trailer = String.format("2%010d", sumLinesData);

        writeLine(filename, trailer);

    }

    public static void writeLine(String filename, String line) {

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(filename, true));
        } catch (IOException e) {
            System.out.printf("Erro ao abrir arquivo: %s.\n", e.getMessage());
        }

        try {
            writer.append(line + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.printf("Erro ao escrever arquivo: %s.\n", e.getMessage());
        }

    }

    @Operation(summary = "Importar arquivo TXT com lista de temas e tags", description = "Importa arquivo TXT com uma lista de temas e tags")
    public static void importPlainTextFile(MultipartFile file) {

        BufferedReader reader = null;
        String line, typeLine, destino, nome;
        int sumDataLineRead = 0;
        int sumDataLineFile;

        try {
            InputStream inputStream = file.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
        } catch (IOException e) {
            System.out.printf("Erro ao abrir arquivo: %s.\n", e.getMessage());
        }

        try {

            line = reader.readLine();

            while (line != null) {

                typeLine = line.substring(0, 1);

                if (typeLine.equals("0")) {
                    // Caso necessário, validar arquivo
                } else if (typeLine.equals("1")) {
                    destino = line.substring(1, 2);

                    nome = line.substring(2, 102).trim();

                    if (destino.equals("1")) {
                        //Tema
                        CadastroTemaDto cadastroTemaDto = new CadastroTemaDto();
                        cadastroTemaDto.setNome(nome);
                        temaService.cadastrar(cadastroTemaDto);
                    } else if (destino.equals("2")) {
                        //Tag
                        CadastroTagDTO cadastroTagDTO = new CadastroTagDTO();
                        cadastroTagDTO.setNome(nome);
                        tagService.cadastrar(cadastroTagDTO);
                    }

                    sumDataLineRead++;

                } else if (typeLine.equals("2")) {
                    sumDataLineFile = Integer.parseInt(line.substring(1, 11));

                    if (sumDataLineRead != sumDataLineFile) {
                        System.out.printf("Erro ao ler arquivo! Quantidade de linhas de dados lidas (%d) diferente da quantidade de linhas de dados informada no trailer (%d).\n", sumDataLineRead, sumDataLineFile);
                    }


                }

                line = reader.readLine();

            }

            reader.close();

        } catch (IOException e) {
            System.out.printf("Erro ao ler o arquivo! erro: %s\n", e.getMessage());
        }

    }

}
