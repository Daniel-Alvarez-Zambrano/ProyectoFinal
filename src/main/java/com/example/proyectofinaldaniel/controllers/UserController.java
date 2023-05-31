package com.example.proyectofinaldaniel.controllers;

import com.example.proyectofinaldaniel.entities.Cart;
import com.example.proyectofinaldaniel.entities.ErrorResponse;
import com.example.proyectofinaldaniel.entities.Role;
import com.example.proyectofinaldaniel.entities.dto.AuthRequest;
import com.example.proyectofinaldaniel.entities.UserEntity;
import com.example.proyectofinaldaniel.repositories.CartRepository;
import com.example.proyectofinaldaniel.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserRepository repository;
    private AuthenticationManager authManager;
    private PasswordEncoder encoderPass;
    private CartRepository cartRepository;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequest user){
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(repository.findByEmail(user.getEmail()).orElseThrow(() ->new UsernameNotFoundException("No se ha encontrado el usuario con email"+user.getEmail())));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody AuthRequest request, HttpServletRequest httpRequest) throws ServletException {
        if(repository.existsByEmail(request.getEmail())) {
            return new ResponseEntity(new ErrorResponse("EMAIL_ALREADY_EXISTS", "Enter another email"), HttpStatus.BAD_REQUEST);
        }
        if(!request.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return new ResponseEntity(new ErrorResponse("WRONG_EMAIL_FORMAT", "Enter a valid email"), HttpStatus.BAD_REQUEST);
        }
        if(request.getPassword().length() < 6) {
            return new ResponseEntity(new ErrorResponse("WEAK_PASSWORD", "Enter a valid password"), HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(encoderPass.encode(request.getPassword()));
        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);
        user.setRole(List.of(Role.USER));
        repository.save(user);
        return ResponseEntity.ok(user);
    }
}
