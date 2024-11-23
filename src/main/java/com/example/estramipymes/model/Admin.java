package com.example.estramipymes.model;

import com.example.estramipymes.util.EncryptionUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 25, nullable = false)
    private String email;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Transient
    @JsonIgnore
    public String getDecryptedPassword() {
        try {
            return EncryptionUtil.decrypt(this.password);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting password", e);
        }
    }
    
}