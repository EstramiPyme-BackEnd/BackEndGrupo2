package com.example.estramipymes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estramipymes.exception.ResourceNotFoundException;
import com.example.estramipymes.model.Student;
import com.example.estramipymes.repository.StudentRepository;

//import com.example.estramipymes.repository.TeacherRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // @Autowired
    // private TeacherRepository teacherRepository;

    // Crear un estudiante
    public Student createStudent(Student student) {
        Student existingStudent = studentRepository.findByEmail(student.getEmail());
        if (existingStudent != null)
            return null;

        return studentRepository.save(student);
    }

    // Obtener los datos de un estudiante por ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
    }

    // Obtener todos los estudiantes
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Actualizar algunos datos del estudiante

    public Student updateStudentPartial(Long id, Student studentDetails) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));

        if (studentDetails.getName() != null) {
            existingStudent.setName(studentDetails.getName());
        }
        if (studentDetails.getEmail() != null) {
            existingStudent.setEmail(studentDetails.getEmail());
        }
        if (studentDetails.getHaceParteProyecto() != null) {
            existingStudent.setHaceParteProyecto(studentDetails.getHaceParteProyecto());
        }

        return studentRepository.save(existingStudent);
    }

    // Eliminar un estudiante por ID
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}
