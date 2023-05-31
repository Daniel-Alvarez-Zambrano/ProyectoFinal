package com.example.proyectofinaldaniel.controllers;

import com.example.proyectofinaldaniel.entities.FurnitureType;
import com.example.proyectofinaldaniel.entities.Product;
import com.example.proyectofinaldaniel.entities.dto.CreateProduct;
import com.example.proyectofinaldaniel.repositories.ProductRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository repository;

    @GetMapping("/list")
    public List<Product> find(@RequestParam(required = false) String type){
        return StringUtils.isNotBlank(type) ? repository.findProductByType(FurnitureType.valueOf(type))
                : repository.findAll();
    }

    @PostMapping
    public ResponseEntity saveProduct(CreateProduct product) {
        Product newProduct = new Product();
        newProduct.setImage(product.getImage());
        newProduct.setDepth(product.getDepth());
        newProduct.setHeight(product.getHeight());
        newProduct.setWidth(product.getWidth());
        newProduct.setName(product.getName());
        newProduct.setColor(product.getColor());
        newProduct.setPrice(product.getPrice());
        newProduct.setStock(product.getStock());
        newProduct.setType(product.getType().stream().map(FurnitureType::valueOf).toList());
        this.repository.save(newProduct);
        return ResponseEntity.ok("New product saved!");
    }
}
