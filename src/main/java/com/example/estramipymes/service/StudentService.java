package com.example.estramipymes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estramipymes.exception.ServiceResponse;
import com.example.estramipymes.model.Student;
import com.example.estramipymes.repository.StudentRepository;

//import com.example.estramipymes.repository.TeacherRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Obtener(GET) lista de Students
     public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students;
    }
    // Obtener (GET)Student por ID
    public Student getStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        return student;
    }

     // Obtener(GET) Student por email
     public Student getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        return student;
    }

    // Crear(POST) Student
    public ServiceResponse<Student> createStudent(Student student) {
        Student existingStudent = studentRepository.findByEmail(student.getEmail());
        if (existingStudent != null) {
            return ServiceResponse.error("El correo electrónico '" + student.getEmail() + "' ya está registrado");
        }

        Student savedStudent = studentRepository.save(student);
        return ServiceResponse.success(savedStudent);
    }
    


    // Actualizar(PUT) Student por ID
    public ServiceResponse<Student> updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            return ServiceResponse.error("Usuario no encontrado");
        }

        if (student.getEmail() != null && !student.getEmail().equals(existingStudent.getEmail())) {
            Student studentWithNewEmail = studentRepository.findByEmail(student.getEmail());
            if (studentWithNewEmail != null) {
                return ServiceResponse.error("El correo electrónico '" + student.getEmail() + "' ya está en uso por otro usuario");
            }
        }

        existingStudent.setEmail(student.getEmail() == null ? existingStudent.getEmail() : student.getEmail());
   
        Student savedStudent = studentRepository.save(existingStudent);
        return ServiceResponse.success(savedStudent);
    }

    // Eliminar(DELETE) Student por ID
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
