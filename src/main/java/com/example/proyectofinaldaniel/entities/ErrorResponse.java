package com.example.proyectofinaldaniel.entities;

import lombok.Data;

@Data
public class ErrorResponse {

    private final String cause;
    private final String message;
}
