package com.example.proyectofinaldaniel.services;

import com.example.proyectofinaldaniel.entities.Cart;
import com.example.proyectofinaldaniel.entities.CartProduct;
import com.example.proyectofinaldaniel.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@AllArgsConstructor
public class EmailService {
    public String getEmailBody(Cart cart) throws Exception {
        return new ClassPathResource("correo.html", this.getClass().getClassLoader())
                .getContentAsString(StandardCharsets.UTF_8)
                .replace("${products}", getProductHtml(cart));
    }

    private String getProductHtml(Cart cart) throws IOException {
        String htmlTemplate = new ClassPathResource("product.html", this.getClass().getClassLoader()).getContentAsString(StandardCharsets.UTF_8);
        String products = new String();
        for (CartProduct cartProduct :
                cart.getProducts()) {
            String productHtml = htmlTemplate.replace("${image}", cartProduct.getProduct().getImage());
            productHtml = productHtml.replace("${name}", cartProduct.getProduct().getName());
            productHtml = productHtml.replace("${quantity}", String.valueOf(cartProduct.getQuantity()));
            productHtml = productHtml.replace("${price}", String.valueOf(cartProduct.getQuantity() * cartProduct.getProduct().getPrice()));
            products = products.concat(productHtml);
        }
        return products;
    }
}
