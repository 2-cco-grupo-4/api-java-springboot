package com.example.picmejava.infra.exception;

public class ConflitoNoCadastroException extends RuntimeException{

    public ConflitoNoCadastroException(String mensage){
        super(mensage);
    }
}
