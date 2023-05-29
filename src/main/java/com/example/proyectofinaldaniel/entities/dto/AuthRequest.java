package com.example.proyectofinaldaniel.entities.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class AuthRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
