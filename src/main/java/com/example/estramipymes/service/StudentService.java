package com.example.estramipymes.service;

import com.example.estramipymes.model.Student;
import com.example.estramipymes.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Integer id, Student studentDetails) {
        Student student = getStudentById(id);
        
        student.setFirst_name(studentDetails.getFirst_name());
        student.setLast_name(studentDetails.getLast_name());
        student.setEmail(studentDetails.getEmail());
        student.setPassword(studentDetails.getPassword());
        student.setPhone(studentDetails.getPhone());
        student.setCompany_id(studentDetails.getCompany_id());
        student.setRole_id(studentDetails.getRole_id());

        return studentRepository.save(student);
    }

    public void deleteStudent(Integer id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}