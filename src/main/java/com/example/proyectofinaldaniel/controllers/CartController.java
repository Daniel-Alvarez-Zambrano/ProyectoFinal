package com.example.proyectofinaldaniel.controllers;

import com.example.proyectofinaldaniel.entities.Cart;
import com.example.proyectofinaldaniel.entities.UserEntity;
import com.example.proyectofinaldaniel.entities.dto.UpdateCart;
import com.example.proyectofinaldaniel.services.CartService;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private CartService service;
    private JavaMailSender emailSender;

    @GetMapping("/products")
    public Cart getCart() {
        return service.getCart(getUser().getCart().getId());
    }

    @PutMapping("/product")
    public Cart addProductToCart(@RequestBody UpdateCart updateCart) {
        return service.addItemToCart(getUser().getCart().getId(), updateCart.getProductId());
    }

    @GetMapping("/checkout")
    public String sendCheckout() throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        message.setFrom("furnituredaniel@spring.com");
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("albertoaretino3@gmail.com"));
        message.setSubject("Correo spring");
        String htmlTemplate = new ClassPathResource("correo.html", this.getClass().getClassLoader()).getContentAsString(StandardCharsets.UTF_8);
        message.setContent(htmlTemplate, "text/html; charset=utf-8");

        emailSender.send(message);
        return "";
    }

    @DeleteMapping("/remove")
    public Cart removeProduct(@RequestParam(required = true) Long productId) {
        Cart cart = this.service.getCart(getUser().getCart().getId());
        service.saveCart(cart.getProducts().stream()
                .filter(product -> product.getProduct().getId().equals(productId))
                .filter(product -> product.removeQuantity().getQuantity() == 0)
                .map(product -> cart.getProducts().remove(product))
                .map(product -> cart).findAny().orElse(cart));
        return service.getCart(cart.getId());
    }


    private UserEntity getUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
