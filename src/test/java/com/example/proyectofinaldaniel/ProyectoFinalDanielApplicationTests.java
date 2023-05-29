package com.example.proyectofinaldaniel;

import com.example.proyectofinaldaniel.entities.Cart;
import com.example.proyectofinaldaniel.entities.FurnitureType;
import com.example.proyectofinaldaniel.entities.Product;
import com.example.proyectofinaldaniel.repositories.CartRepository;
import com.example.proyectofinaldaniel.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProyectoFinalDanielApplicationTests {

    @Autowired
    ProductRepository repository;

    @Test
    void contextLoads() {
        Product entity = new Product();
        entity.setName("Mesa de cocina CLOE");
        entity.setHeight(67);
        entity.setWidth(107);
        entity.setColor("Blanco y Roble");
        entity.setType(List.of(FurnitureType.COCINA));
        entity.setStock(500);
        entity.setPrice(69.99);
        repository.save(entity);
//        Cart entity = new Cart();
//        entity.setCartPrice(12);
//        repository.save(entity);
    }

}
