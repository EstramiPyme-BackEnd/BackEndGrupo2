package com.example.estramipymes.controller;

import java.util.List;
import java.util.Map;

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

import com.example.estramipymes.model.Teacher;
import com.example.estramipymes.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // Crear un Profesor
     @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Map<String, Object> payload) {
        // Extraer datos del payload
        String companyEmail = (String) payload.get("company_email");
        if (companyEmail == null || companyEmail.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Teacher teacher = new Teacher();
        teacher.setFirst_name((String) payload.get("first_name"));
        teacher.setLast_name((String) payload.get("last_name"));
        teacher.setEmail((String) payload.get("email"));
        teacher.setPassword((String) payload.get("password"));
        teacher.setPhone((String) payload.get("phone"));

        // Crear el estudiante usando el servicio
        Teacher createdTeacher = teacherService.createTeacher(teacher, companyEmail);

        if (createdTeacher == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }


    // Ver los datos de un Profesor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Teacher Teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(Teacher);
    }

    // Ver todos los Profesores
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> Teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(Teachers);
    }

    // Ver el profesor asignado
    // @GetMapping("/teacher/{id}")
    // public ResponseEntity<?> getAssignedTeacher(@PathVariable Long id) {
    //     return ResponseEntity.ok(TeacherService.getAssignedTeacher(id));
    // }

    // Actualizar algunos datos del Profesor
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacherPartial(@PathVariable Long id, @RequestBody Teacher Teacher) {
        Teacher updatedTeacher = teacherService.updateTeacherPartial(id, Teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    // Eliminar todos los datos del Profesor
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully");
    }
}
