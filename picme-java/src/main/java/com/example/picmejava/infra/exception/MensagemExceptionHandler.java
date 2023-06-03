package com.example.picmejava.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagemExceptionHandler {

    private LocalDateTime timestemp;
    private Integer status;
    private String message;
}
