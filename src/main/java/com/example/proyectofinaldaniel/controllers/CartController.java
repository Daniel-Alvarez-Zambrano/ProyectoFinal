package com.example.proyectofinaldaniel.controllers;

import com.example.proyectofinaldaniel.entities.Cart;
import com.example.proyectofinaldaniel.entities.dto.UpdateCart;
import com.example.proyectofinaldaniel.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private CartService service;
    private JavaMailSender emailSender;

    @GetMapping("/{id}/products")
    public Cart getCart(@PathVariable Long id){
        return service.getCart(id);
    }

    @PutMapping("/{id}/product")
    public Cart addProductToCart(@PathVariable Long id, @RequestBody UpdateCart updateCart){
        return service.addItemToCart(id, updateCart.getProductId());
    }
    @GetMapping("/{id}/checkout")
    public String sendCheckout(@PathVariable Long id){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("furnitureDaniel@spring.com");
        message.setTo("ivandaniel1231@hotmail.com");
        message.setSubject("Este es un email desde spring");
        message.setText("Este es un email desde spring");
        emailSender.send(message);
        return "";
    }
}
