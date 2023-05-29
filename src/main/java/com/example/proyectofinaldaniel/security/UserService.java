package com.example.proyectofinaldaniel.security;

import com.example.proyectofinaldaniel.entities.Role;
import com.example.proyectofinaldaniel.entities.UserEntity;
import com.example.proyectofinaldaniel.repositories.UserRepository;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
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
        UserEntity entity = repository.findByEmail(uEmail).orElseThrow(() ->new UsernameNotFoundException("No se ha encontrado el usuario con email"+uEmail));
        return new User(entity.getEmail(), entity.getPassword(), mapRolesToAuthorities(entity.getRole()));
    }
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
        return roles.stream().map(Role::getValue).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
