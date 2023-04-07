package com.example.picmejava.exceptionhandler;

import java.time.LocalDateTime;

public class MensagemExceptionHandler {

    private LocalDateTime timestemp;
    private Integer status;
    private String message;

    public MensagemExceptionHandler(LocalDateTime timestemp, Integer status, String message) {
        this.timestemp = timestemp;
        this.status = status;
        this.message = message;
    }

    public LocalDateTime getTimestemp() {
        return timestemp;
    }

    public void setTimestemp(LocalDateTime timestemp) {
        this.timestemp = timestemp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
