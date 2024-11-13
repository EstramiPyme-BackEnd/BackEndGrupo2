package com.example.estramipymes.service;

import com.example.estramipymes.model.Admin;
import com.example.estramipymes.model.Student;
import com.example.estramipymes.model.Teacher;
import com.example.estramipymes.repository.AdminRepository;
import com.example.estramipymes.repository.StudentRepository;
import com.example.estramipymes.repository.TeacherRepository;
import com.example.estramipymes.repository.CompanyRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;
    private final CompanyRepository companyRepository;

    public UserService(StudentRepository studentRepository, TeacherRepository teacherRepository, AdminRepository adminRepository,CompanyRepository companyRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.adminRepository = adminRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Primero buscar en estudiantes
        var student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
            return org.springframework.security.core.userdetails.User
                .withUsername(student.get().getEmail())
                .password(student.get().getPassword())
                .roles("STUDENT")
                .build();
        }
        // buscar company
        var company = companyRepository.findByEmail(email);
        if (company.isPresent()) {
            return org.springframework.security.core.userdetails.User
                .withUsername(company.get().getEmail())
                .password(company.get().getPassword())
                .roles("COMPANY")
                .build();
        }

        // Luego buscar en profesores
        var teacher = teacherRepository.findByEmail(email);
        if (teacher.isPresent()) {
            return org.springframework.security.core.userdetails.User
                .withUsername(teacher.get().getEmail())
                .password(teacher.get().getPassword())
                .roles("TEACHER")
                .build();
        }

        // Finalmente, buscar en administradores
        var admin = adminRepository.findByEmail(email);
        if (admin.isPresent()) {
            return org.springframework.security.core.userdetails.User
                .withUsername(admin.get().getEmail())
                .password(admin.get().getPassword())
                .roles("ADMIN")
                .build();
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
