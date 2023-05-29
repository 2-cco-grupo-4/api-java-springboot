package com.example.picmejava.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class EntidadeNaoCadastradaException extends RuntimeException{

    public EntidadeNaoCadastradaException(String message) {
        super(message);
    }
}
