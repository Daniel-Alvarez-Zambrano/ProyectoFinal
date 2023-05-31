package com.example.proyectofinaldaniel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "cart_products", joinColumns = {@JoinColumn(name = "cart_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<CartProduct> products = new ArrayList<>();
    private double cartPrice;
    @OneToOne
    @JsonIgnore
    private UserEntity user;
    public Cart addToCart(CartProduct product){
        this.products.add(product);
        return this;
    }
}
