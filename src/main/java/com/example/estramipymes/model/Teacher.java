package com.example.estramipymes.model;

import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
      
@Entity
@Data
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "profesorParteProyecto", nullable = false)
    private Boolean profesorParteProyecto = false;
    

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<User> users;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Student> students;
}