package com.example.picmejava.s3;

import com.example.picmejava.infra.exception.S3UploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class S3<T> {

    private String bucket = "picme-s3";

    @Autowired
    private S3Service service;

    private String path = "";

    private final Logger logger = LoggerFactory.getLogger(S3.class);

    public String putImage(MultipartFile image) {

        String imageId = UUID.randomUUID().toString();
        path = "perfil/"+ imageId;

        logger.info("Inserindo imagem: " + bucket + " e no caminho: " + path);

        try {
            service.putObject(
                    bucket,
                    path,
                    image.getBytes(),
                    image.getContentType()
            );

        } catch (IOException e) {
            throw new S3UploadException("Erro ao upload imagem: " + e.getMessage());
        }
        return "perfil/" + imageId;
    }

    public byte[] getImage(String pathImage) {
        logger.info("Retornando imagem: " + bucket + " caminho: " + path);

        return service.getObject(bucket, pathImage);
    }

}
