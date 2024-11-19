package com.example.estramipymes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.estramipymes.model.Teacher;
import com.example.estramipymes.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService TeacherService;

    // Crear un estudiante
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher Teacher) {
        Teacher createdTeacher = TeacherService.createTeacher(Teacher);
        return ResponseEntity.ok(createdTeacher);
    }

    // Ver los datos de un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Teacher Teacher = TeacherService.getTeacherById(id);
        return ResponseEntity.ok(Teacher);
    }

    // Ver todos los estudiantes
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> Teachers = TeacherService.getAllTeachers();
        return ResponseEntity.ok(Teachers);
    }

    // Ver el profesor asignado al estudiante
    // @GetMapping("/teacher/{id}")
    // public ResponseEntity<?> getAssignedTeacher(@PathVariable Long id) {
    //     return ResponseEntity.ok(TeacherService.getAssignedTeacher(id));
    // }

    // Actualizar algunos datos del estudiante
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacherPartial(@PathVariable Long id, @RequestBody Teacher Teacher) {
        Teacher updatedTeacher = TeacherService.updateTeacherPartial(id, Teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    // Eliminar todos los datos del estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        TeacherService.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully");
    }
}
