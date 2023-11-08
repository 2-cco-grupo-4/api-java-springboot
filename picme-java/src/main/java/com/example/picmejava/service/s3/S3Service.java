package com.example.picmejava.service.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;

@Service
public class S3Service {

    public void putObject(String bucketName, String fileName) {
        String key_name = fileName+"teste";

        System.out.format("Uploading %s to S3 bucket %s...\n", fileName, bucketName);
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
        try {
            s3.putObject(bucketName, key_name, new File(fileName));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        System.out.println("Done!");
    }
}