package com.example.estramipymes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estramipymes.exception.ServiceResponse;
import com.example.estramipymes.model.Teacher;
import com.example.estramipymes.repository.TeacherRepository;


//import com.example.estramipymes.repository.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    // Obtener(GET) lista de Teachers
     public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers;
    }
    // Obtener (GET)Teacher por ID
    public Teacher getTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        return teacher;
    }

     // Obtener(GET) Teacher por email
     public Teacher getTeacherByEmail(String email) {
        Teacher teacher = teacherRepository.findByEmail(email);
        return teacher;
    }

    // Crear(POST) Teacher
    public ServiceResponse<Teacher> createTeacher(Teacher teacher) {
        Teacher existingTeacher = teacherRepository.findByEmail(teacher.getEmail());
        if (existingTeacher != null) {
            return ServiceResponse.error("El correo electrónico '" + teacher.getEmail() + "' ya está registrado");
        }

        Teacher savedTeacher = teacherRepository.save(teacher);
        return ServiceResponse.success(savedTeacher);
    }
    


    // Actualizar(PUT) Teacher por ID
    public ServiceResponse<Teacher> updateTeacher(Long id, Teacher teacher) {
        Teacher existingTeacher = teacherRepository.findById(id).orElse(null);

        if (existingTeacher == null) {
            return ServiceResponse.error("Usuario no encontrado");
        }

        if (teacher.getEmail() != null && !teacher.getEmail().equals(existingTeacher.getEmail())) {
            Teacher teacherWithNewEmail = teacherRepository.findByEmail(teacher.getEmail());
            if (teacherWithNewEmail != null) {
                return ServiceResponse.error("El correo electrónico '" + teacher.getEmail() + "' ya está en uso por otro usuario");
            }
        }

        existingTeacher.setEmail(teacher.getEmail() == null ? existingTeacher.getEmail() : teacher.getEmail());
   
        Teacher savedTeacher = teacherRepository.save(existingTeacher);
        return ServiceResponse.success(savedTeacher);
    }

    // Eliminar(DELETE) Teacher por ID
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    
}
