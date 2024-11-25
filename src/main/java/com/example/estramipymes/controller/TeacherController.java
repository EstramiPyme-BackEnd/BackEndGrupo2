package com.example.estramipymes.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.estramipymes.dto.ApiResponse;
import com.example.estramipymes.exception.ServiceResponse;
import com.example.estramipymes.model.Teacher;
import com.example.estramipymes.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

     // Crear un Profesor
    @PostMapping()
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        ServiceResponse<Teacher> response = teacherService.createTeacher(teacher);
        
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(response.getMessage()));
        }
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(response.getData());
    }

    // Ver los datos de un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacher(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacher(id);
        if (teacher == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Usuario no encontrado"));
        }
        return ResponseEntity.ok(teacher);
    }

    // Ver todos los estudiantes
    @GetMapping
    public ResponseEntity<?> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/")
    public ResponseEntity<?> getTeacherByEmail(@RequestParam(required = false) String email) {       
        Teacher teacher = teacherService.getTeacherByEmail(email);
        if (teacher != null) {
            return ResponseEntity.ok(Collections.singletonList(teacher));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Usuario no encontrado"));
        }
    }

    // Actualizar algunos datos del Profesor
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        ServiceResponse<Teacher> response = teacherService.updateTeacher(id, teacher);
        
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(response.getMessage()));
        }
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(response.getData());
    }
    // Eliminar todos los datos del Profesor
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully");
    }
}
