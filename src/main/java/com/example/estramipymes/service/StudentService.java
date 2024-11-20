package com.example.estramipymes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estramipymes.exception.ResourceNotFoundException;
import com.example.estramipymes.model.Company;
import com.example.estramipymes.model.Role;
import com.example.estramipymes.model.Student;
import com.example.estramipymes.repository.CompanyRepository;
import com.example.estramipymes.repository.RoleRepository;
import com.example.estramipymes.repository.StudentRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//import com.example.estramipymes.repository.TeacherRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
private PasswordEncoder passwordEncoder;
    // @Autowired
    // private TeacherRepository teacherRepository;

    // Crear un estudiante
    public Student createStudent(Student student, String companyEmail) {
        // Verificar si el estudiante ya existe basado en el email
        Optional<Student> existingStudent = studentRepository.findByEmail(student.getEmail());
        if (existingStudent.isPresent()) {
            throw new RuntimeException("El estudiante con el email proporcionado ya existe.");
        }
    
        // Buscar el rol de estudiante
        Optional<Role> role = roleRepository.findByDescription(Role.Description.student);
        if (role.isEmpty()) {
            throw new RuntimeException("El rol de estudiante no existe.");
        }
        student.setRole_id(role.get());
    
        // Buscar la compañía basada en el email proporcionado
        Optional<Company> company = companyRepository.findByEmail(companyEmail);
        if (company.isEmpty()) {
            throw new RuntimeException("No se encontró una compañía con el email proporcionado.");
        }
        student.setCompany_id(company.get());

        // Codificar la contraseña antes de guardarla
        String encodedPassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(encodedPassword);
    
        // Guardar y devolver el nuevo estudiante
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

    // Obtener el profesor asignado a un estudiante
    // public Teacher getAssignedTeacher(Long studentId) {
    //     Student student = studentRepository.findById(studentId)
    //             .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));

    //     Long companyId = student.getCompany_id().getId();
    //     Optional<Teacher> assignedTeacher = teacherRepository.findByCompanyId(companyId);

    //     return assignedTeacher.orElseThrow(() -> new ResourceNotFoundException("No teacher assigned for student ID: " + studentId));
    // }

    // Actualizar algunos datos del estudiante
    public Student updateStudentPartial(Long id, Student studentDetails) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));

        if (studentDetails.getFirst_name() != null) {
            existingStudent.setFirst_name(studentDetails.getFirst_name());
        }
        if (studentDetails.getLast_name() != null) {
            existingStudent.setLast_name(studentDetails.getLast_name());
        }
        if (studentDetails.getEmail() != null) {
            existingStudent.setEmail(studentDetails.getEmail());
        }
        if (studentDetails.getPhone() != null) {
            existingStudent.setPhone(studentDetails.getPhone());
        }
        // Codificar la contraseña si se está actualizando
        if (studentDetails.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(studentDetails.getPassword());
            existingStudent.setPassword(encodedPassword);
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
