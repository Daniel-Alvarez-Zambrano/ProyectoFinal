package com.example.proyectofinaldaniel.services;

import com.example.proyectofinaldaniel.entities.Cart;
import com.example.proyectofinaldaniel.entities.NotFoundException;
import com.example.proyectofinaldaniel.repositories.CartRepository;
import com.example.proyectofinaldaniel.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart addItemToCart(Long cartId, Long productId) {
        Cart entity = cartRepository.findById(cartId)
                .map(cart -> cart.addToCart(productRepository
                        .findById(productId)
                        .orElseThrow(() -> new NotFoundException("Producto no encontrado"))))
                .orElseThrow(() -> new NotFoundException("Carrito no encontrado"));
        return cartRepository.save(entity);
    }

    public Cart getCart(Long id){
        return cartRepository.findById(id).orElseThrow(() ->new NotFoundException("Carrito no encontrado"));
    }
}
