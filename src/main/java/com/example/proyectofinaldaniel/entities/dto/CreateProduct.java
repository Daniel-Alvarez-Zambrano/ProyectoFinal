package com.example.proyectofinaldaniel.entities.dto;

import com.example.proyectofinaldaniel.entities.FurnitureType;
import lombok.Data;

import java.util.List;

@Data
public class CreateProduct {
    private String name;
    private List<String> type;
    private double price;
    private double height;
    private double width;
    private double depth;
    private String color;
    private int stock;
    private String image;
}
