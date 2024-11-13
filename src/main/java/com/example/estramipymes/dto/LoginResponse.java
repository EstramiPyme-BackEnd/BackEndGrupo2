package com.example.estramipymes.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String message;
    private boolean success;
    
    public LoginResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
    
    public LoginResponse() {
    }
}