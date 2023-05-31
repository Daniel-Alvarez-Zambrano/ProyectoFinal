package com.example.proyectofinaldaniel.security;

import com.example.proyectofinaldaniel.entities.ValidationException;
import com.example.proyectofinaldaniel.entities.Role;
import com.example.proyectofinaldaniel.entities.UserEntity;
import com.example.proyectofinaldaniel.repositories.UserRepository;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String uEmail) throws UsernameNotFoundException {
        if(!repository.existsByEmail(uEmail)) {
            throw new ValidationException("USER_NOT_EXISTS", "Enter another email");
        }
        UserEntity entity = repository.findByEmail(uEmail).orElseThrow(() ->new UsernameNotFoundException("No se ha encontrado el usuario con email"+uEmail));
        if(!entity.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new ValidationException("WRONG_EMAIL_FORMAT", "Enter a valid email");
        }
        if(entity.getPassword().length() < 6) {
            throw new ValidationException("WEAK_PASSWORD", "Enter a valid password");
        }
        return entity;
    }
}
