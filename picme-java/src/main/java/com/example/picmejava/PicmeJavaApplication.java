package com.example.picmejava;

import com.example.picmejava.instagram.InstagramClient;
import com.example.picmejava.lista.Lista;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

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
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class PicmeJavaApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int choose = 0;
            System.out.println("Escolha uma opção:\n1- SpringBoot API\n2- Demonstração Lista\n0- Sair");
            choose = scanner.nextInt();
            switch (choose) {
                case 1 -> SpringApplication.run(PicmeJavaApplication.class, args);
                case 2 -> testesLista();
                case 0 -> System.exit(0);
                default -> System.out.println("Não existe essa opção");
            }
        }

    }

    public static void testesLista() {
        Lista<Integer> listaIntegers = new Lista<>();

        Integer f1 = 9;
        Integer f2 = 1;
        Integer f3 = 5;
        Integer f4 = 3;

        listaIntegers.add(f1);
        listaIntegers.add(f2);
        listaIntegers.add(f3);
        listaIntegers.add(f4);
        System.out.println("Lista de números antes do Bubble Sort:");
        for (int i = 0; i < listaIntegers.size(); i++) {
            if (listaIntegers.get(i) != null) {
                System.out.println(listaIntegers.get(i));
            }
        }


        listaIntegers.ordenarBubbleSort();

        System.out.println("Lista de números depois do Bubble Sort:");
        for (int i = 0; i < listaIntegers.size(); i++) {
            System.out.println(listaIntegers.get(i));
        }

        listaIntegers.set(1, 95);

        System.out.println("Lista de números depois de atualizar o segundo elemento:");
        for (int i = 0; i < listaIntegers.size(); i++) {
            System.out.println(listaIntegers.get(i));
        }

        listaIntegers.deletarPorIndex(0);

        System.out.println("Lista de números depois de remover o primeiro elemento:");
        for (int i = 0; i < listaIntegers.size(); i++) {
            System.out.println(listaIntegers.get(i));
        }


        System.out.println("Lista de números ordenadas novamente");
        listaIntegers.ordenarBubbleSort();
        for (int i = 0; i < listaIntegers.size(); i++) {
            if (listaIntegers.get(i) != null) {
                System.out.println(listaIntegers.get(i));
            }
        }




        System.out.println("Existe o número 5? ");
        System.out.println("Está no Index: " + listaIntegers.buscaBinaria(5) + "\n\n");
    }
}
