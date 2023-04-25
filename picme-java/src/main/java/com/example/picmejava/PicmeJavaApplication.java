package com.example.picmejava;

import com.example.picmejava.lista.Lista;
import com.example.picmejava.model.Fotografo;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@OpenAPIDefinition(
        info = @Info(
                title = "PICME API",
                version = "0.7.2"
        )
)

@SpringBootApplication
public class PicmeJavaApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choose = 0 ;
        System.out.println("Escolha uma opção:\n1- SpringBoot API\n2- Demonstração Lista");
        choose = scanner.nextInt();
        switch(choose){
            case 1 -> SpringApplication.run(PicmeJavaApplication.class, args);
            case 2 -> testesLista();
            default -> System.out.println("Não existe essa opção");
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
        for (int i = 0; i <listaIntegers.size(); i++) {
            if (listaIntegers.get(i) != null) {
                System.out.println(listaIntegers.get(i));
            }
        }


        listaIntegers.ordenarBubbleSort();

        System.out.println("Lista de números depois do Bubble Sort:");
        for (int i = 0; i < listaIntegers.size(); i++) {
            System.out.println(listaIntegers.get(i));
        }

        listaIntegers.set(1, f4);

        System.out.println("Lista de números depois de atualizar o segundo elemento:");
        for (int i = 0; i < listaIntegers.size(); i++) {
            System.out.println(listaIntegers.get(i));
        }

        listaIntegers.deletarPorIndex(0);

        System.out.println("Lista de números depois de remover o primeiro elemento:");
        for (int i = 0; i < listaIntegers.size(); i++) {
            System.out.println(listaIntegers.get(i));
        }
    }
}
