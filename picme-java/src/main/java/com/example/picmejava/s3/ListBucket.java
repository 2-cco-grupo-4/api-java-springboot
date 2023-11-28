package com.example.picmejava.s3;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

import java.util.List;

public class ListBucket {
    public static void main(String[] args) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();


        List<Bucket> buckets = s3.listBuckets();
        System.out.println("Your {S3} buckets are:");
        for (Bucket b : buckets) {
            System.out.println("* " + b.getName());
        }
    }
}
