package com.example.libraryProject.Security.service;

import com.example.libraryProject.Entity.Manager;
import com.example.libraryProject.Repository.ManagerRepository;
import com.example.libraryProject.Security.ManagerContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final ManagerRepository managerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = managerRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("UserName Is NotFound"));
        return ManagerContext.builder()
                .username(manager.getManagerName())
                .password(manager.getPassword())
                .roles(manager.getRole())
                .build();
    }
}
