package com.example.picmejava.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    public S3Client s3Client() {
        SdkHttpClient httpClient = UrlConnectionHttpClient.builder().build();
        AwsSessionCredentials credentials = AwsSessionCredentials.create(
                "ASIAQUWW36CJXHOHYRAA",
                "uIhQ5wawxbSVxL3nLQoYhsuhKXU0OAW4qS72GBfY",
                "FwoGZXIvYXdzEKb//////////wEaDKnONr9GNuG7BbxX3CLJAV8xAgDC7ItQ29Mleo8GUwDKM14VZm8njSj9bc9rQP3O7nH1DrVRpaa9x+vZL1EVwH8iuBQinT6uz7w1KYvUadqbtLdc/3Ov9McWf8UkrIxZhOZ2/wBVMcgkC1SSUfJpa0mQtt8C83xNmpxSA3CFcZ2SbnFLVJ7yQqMujL7k15Pc/fsoY89g3Vdig3E31iHbwTf/Z0Ivy+Jj3d4V/JMvSu1VUs3ElDtWFy6bNyGxfneoHdaJ9xGXuaovTPHKoPboCETReCmpWxqhFyjHiaKrBjIt5Um+d6qt2SA8AEkY496wrcWlAjbkMkQV3mvNn7aLn7qR27c3ZNhIKAlMNOkW"
        );

        return S3Client.builder()
                .region(Region.of(awsRegion))
                .httpClient(httpClient)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}