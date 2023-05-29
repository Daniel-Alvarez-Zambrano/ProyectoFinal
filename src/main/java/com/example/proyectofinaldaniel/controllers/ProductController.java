package com.example.proyectofinaldaniel.controllers;

import com.example.proyectofinaldaniel.entities.FurnitureType;
import com.example.proyectofinaldaniel.entities.Product;
import com.example.proyectofinaldaniel.repositories.ProductRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
