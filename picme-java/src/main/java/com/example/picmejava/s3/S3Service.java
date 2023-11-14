package com.example.picmejava.s3;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

    private final S3Client s3Cliente;

    public S3Service(S3Client s3Cliente) {
        this.s3Cliente = s3Cliente;
    }

    public void upload(String bucketName, String keyName, byte[] file) {

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();

        s3Cliente.putObject(putObjectRequest, RequestBody.fromBytes(file));
    }
}