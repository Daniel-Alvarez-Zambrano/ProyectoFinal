package com.example.proyectofinaldaniel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity = 0;
    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE},mappedBy = "products")
    @JsonIgnore
    private List<Cart> carts = new ArrayList<>();

    public CartProduct addQuantity() {
        return this.setQuantity(++this.quantity);
    }

    public CartProduct removeQuantity() {
        return this.setQuantity(--this.quantity);
    }
}
