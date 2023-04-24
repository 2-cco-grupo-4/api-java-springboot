package com.example.picmejava;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "PICME API",
                version = "0.9"
        )
)

@SpringBootApplication
public class PicmeJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PicmeJavaApplication.class, args);
    }

}
