package com.example.estramipymes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estramipymes.model.Student;

public interface  StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByEmail(String email);  
}
