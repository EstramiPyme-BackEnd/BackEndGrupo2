package com.example.estramipymes.service.impl;

import com.example.estramipymes.dto.LoginRequest;
import com.example.estramipymes.dto.LoginResponse;
import com.example.estramipymes.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
public LoginResponse login(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(),
            loginRequest.getPassword()
        )
    );
    
    SecurityContextHolder.getContext().setAuthentication(authentication);
    
    return new LoginResponse("Login successful", true);  // Usando el constructor directamente
}

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}