package com.example.proyectofinaldaniel.entities;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException{

    private String exceptionCause;
    public ValidationException(String message, String cause){
        super(message);
        this.exceptionCause = cause;
    }
}
