package com.example.proyectofinaldaniel.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCart {
    @NonNull
    private Long productId;
}
