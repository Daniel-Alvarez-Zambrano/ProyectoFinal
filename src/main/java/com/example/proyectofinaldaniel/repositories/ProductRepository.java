package com.example.proyectofinaldaniel.repositories;

import com.example.proyectofinaldaniel.entities.FurnitureType;
import com.example.proyectofinaldaniel.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByType(FurnitureType type);
}
