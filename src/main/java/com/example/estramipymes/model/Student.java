package com.example.estramipymes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data; 
      
@Entity
@Data
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long  id;

    @Column(name = "email", length = 25, nullable = false)
    private String email;

    @Column(name = "name", length = 50, nullable = false)
    private String name; 

    @Column(name = "haceParteProyecto", length = 25, nullable = false)
    private Boolean haceParteProyecto;

}
