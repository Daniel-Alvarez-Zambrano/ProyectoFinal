package com.example.proyectofinaldaniel.services;

import com.example.proyectofinaldaniel.entities.Cart;
import com.example.proyectofinaldaniel.entities.CartProduct;
import com.example.proyectofinaldaniel.entities.NotFoundException;
import com.example.proyectofinaldaniel.repositories.CartRepository;
import com.example.proyectofinaldaniel.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart addItemToCart(Long cartId, Long productId) {
        Cart entity = cartRepository.findById(cartId)
                .map(cart -> {
                    if (cart.getProducts().stream()
                            .map(CartProduct::getProduct)
                            .anyMatch(product -> product.getId().equals(productId))) {
                        cart.getProducts()
                                .stream()
                                .filter(product -> product.getProduct().getId().equals(productId))
                                .map(CartProduct::addQuantity)
                                .findFirst()
                                .get();
                        return cart;
                    }
                    return cart.addToCart(productRepository
                            .findById(productId)
                            .map(product -> new CartProduct().setProduct(product).addQuantity())
                            .orElseThrow(() -> new NotFoundException("Producto no encontrado")));
                })
                .orElseThrow(() -> new NotFoundException("Carrito no encontrado"));
        return cartRepository.save(entity);
    }

    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new NotFoundException("Carrito no encontrado"));
    }

    public Cart saveCart(Cart cart) {
        return this.cartRepository.save(cart);
    }
}
