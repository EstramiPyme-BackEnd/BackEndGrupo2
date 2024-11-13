package com.example.estramipymes.service;

import com.example.estramipymes.dto.LoginRequest;
import com.example.estramipymes.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    void logout();
}
