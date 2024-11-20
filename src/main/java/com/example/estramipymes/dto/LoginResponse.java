package com.example.estramipymes.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String message;
    private boolean success;
    private String role;

    public LoginResponse(String message, boolean success, String role) {
        this.message = message;
        this.success = success;
        this.role = role;
    }

    public LoginResponse() {
    }
}
