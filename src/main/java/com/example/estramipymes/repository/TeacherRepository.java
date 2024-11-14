package com.example.estramipymes.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estramipymes.model.Teacher;
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByEmail(String email);
}
