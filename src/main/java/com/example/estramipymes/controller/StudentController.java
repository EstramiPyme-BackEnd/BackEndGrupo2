package com.example.estramipymes.controller;

import java.util.List;
import java.util.Map;

import com.example.estramipymes.dto.StudentDTO;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.estramipymes.model.Student;
import com.example.estramipymes.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Crear un estudiante
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Map<String, Object> payload) {
        // Extraer datos del payload
        String companyEmail = (String) payload.get("company_email");
        if (companyEmail == null || companyEmail.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Student student = new Student();
        student.setFirst_name((String) payload.get("first_name"));
        student.setLast_name((String) payload.get("last_name"));
        student.setEmail((String) payload.get("email"));
        student.setPassword((String) payload.get("password"));
        student.setPhone((String) payload.get("phone"));

        // Crear el estudiante usando el servicio
        Student createdStudent = studentService.createStudent(student, companyEmail);

        if (createdStudent == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    // Ver los datos de un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDTO);
    }


    // Ver todos los estudiantes
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // Ver el profesor asignado al estudiante
    // @GetMapping("/teacher/{id}")
    // public ResponseEntity<?> getAssignedTeacher(@PathVariable Long id) {
    //     return ResponseEntity.ok(studentService.getAssignedTeacher(id));
    // }

    // Actualizar algunos datos del estudiante
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudentPartial(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudentPartial(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    // Eliminar todos los datos del estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}