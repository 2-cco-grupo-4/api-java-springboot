package com.example.picmejava.s3;

import com.example.picmejava.infra.exception.S3UploadException;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class S3Service {

    private final S3Client s3Client;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void putObject(String bucketName, String keyName, byte[] file, String contentType) {

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .contentType(contentType)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file));
    }

    public byte[] getObject(String bucket, String pathImage) {

        GetObjectRequest objectRequest = GetObjectRequest
                .builder()
                .key(pathImage)
                .bucket(bucket)
                .build();

        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(objectRequest);

        try {
            return response.readAllBytes();
        } catch (IOException e) {
            throw new S3UploadException(e.getMessage());
        }
    }
}