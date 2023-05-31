package com.example.proyectofinaldaniel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ElementCollection(targetClass = FurnitureType.class)
    @CollectionTable(name = "product_type", joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private List <FurnitureType> type;
    private double price;
    private double height;
    private double width;
    private double depth;
    private String color;
    private int stock;
    private String image;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<CartProduct> cartList;
}
