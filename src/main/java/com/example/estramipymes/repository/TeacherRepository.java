package com.example.estramipymes.repository;

import com.example.estramipymes.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.estramipymes.model.Teacher;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}