package com.example.picmejava.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<MensagemExceptionHandler> UsuarioNaoEncontradoException(EntidadeNaoEncontradaException ex){
        MensagemExceptionHandler error = new MensagemExceptionHandler(
                LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ConflitoNoCadastroException.class)
    public ResponseEntity<MensagemExceptionHandler> ConflitoNoCadastroException(EntidadeNaoEncontradaException ex){
        MensagemExceptionHandler error = new MensagemExceptionHandler(
                LocalDateTime.now(), HttpStatus.CONFLICT.value(), ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(S3UploadException.class)
    public ResponseEntity<MensagemExceptionHandler> S3UploadException(S3UploadException ex){
        MensagemExceptionHandler error = new MensagemExceptionHandler(
                LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
