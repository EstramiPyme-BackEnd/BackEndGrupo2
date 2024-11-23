package com.example.estramipymes.model;

// import java.time.YearMonth;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", length = 25, nullable = false)
    private String email;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "typeUser", length = 25, nullable = false)
    private Integer typeUser;

    @Column(name = "sizeCompany", length = 25, nullable = false)
    private Integer sizeCompany;

    @Column(name = "sector", length = 25, nullable = false)
    private Integer sector;
    
    @Column(name = "registerDate", length = 25, nullable = false)
    private String registerDate;

    @Column(name = "isBookDownloaded", length = 25, nullable = false)
    private Boolean isBookDownloaded;

    @Column(name = "isTestDone", length = 25, nullable = false)
    private Boolean isTestDone;    
}

