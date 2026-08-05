package com.example.spring_boot_project.service;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import com.example.spring_boot_project.model.User;
import com.example.spring_boot_project.repository.UserRepo;
@Service
public class CustomUserDetailsService implements UserDetailsService{
    private UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Optional<User> userOpt = this.userRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found with this email");
        }

        User user = userOpt.get();
        var authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName()))
            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
