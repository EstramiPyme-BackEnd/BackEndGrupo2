package com.example.estramipymes.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role_id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", length = 25, nullable = false)
    private String email;

    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "description", length = 25, nullable = false)
    private String description;
    
}
