package com.example.proyectofinaldaniel.entities;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
