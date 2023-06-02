package com.example.picmejava;

import com.example.picmejava.lista.Lista;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@OpenAPIDefinition(
        info = @Info(
                title = "PICME API",
                description = "API REST do Projeto PICME, do grupo 4 de 2CCO de 2023 - SPTech School",
                contact = @Contact(
                        name = "Grupo 4",
                        url = "https://github.com/2-cco-grupo-4"
                ),
                license = @License(name = "UNLICENSED"),
                version = "0.9"
        )
)

@SecurityScheme(
        name = "Bearer", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT"
)

@SpringBootApplication
public class PicmeJavaApplication {

    public static void main(String[] args) {SpringApplication.run(PicmeJavaApplication.class, args);}
}

