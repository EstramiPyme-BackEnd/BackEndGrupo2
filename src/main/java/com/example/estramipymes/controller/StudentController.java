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
import com.example.estramipymes.model.Student;
import com.example.estramipymes.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Crear un estudiante
    @PostMapping()
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        ServiceResponse<Student> response = studentService.createStudent(student);
        
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(response.getMessage()));
        }
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(response.getData());
    }

    // Ver los datos de un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Usuario no encontrado"));
        }
        return ResponseEntity.ok(student);
    }

    // Ver todos los estudiantes
    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/")
    public ResponseEntity<?> getStudentByEmail(@RequestParam(required = false) String email) {       
        Student student = studentService.getStudentByEmail(email);
        if (student != null) {
            return ResponseEntity.ok(Collections.singletonList(student));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Usuario no encontrado"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        ServiceResponse<Student> response = studentService.updateStudent(id, student);
        
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(response.getMessage()));
        }
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(response.getData());
    }

    // Eliminar todos los datos del estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
