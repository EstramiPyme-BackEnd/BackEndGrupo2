package com.example.estramipymes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estramipymes.exception.ResourceNotFoundException;
import com.example.estramipymes.model.Company;
import com.example.estramipymes.model.Role;
import com.example.estramipymes.model.Teacher;
import com.example.estramipymes.repository.CompanyRepository;
import com.example.estramipymes.repository.RoleRepository;
import com.example.estramipymes.repository.TeacherRepository;

//import com.example.estramipymes.repository.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RoleRepository roleRepository;

    // @Autowired
    // private TeacherRepository teacherRepository;

    // Crear un estudiante
    public Teacher createTeacher(Teacher teacher,String companyEmail) {
        Teacher existingTeacher = teacherRepository.findByEmail(teacher.getEmail());
        if (existingTeacher != null)
            return null;

        Role role = roleRepository.findByDescription(Role.Description.teacher);
        if (role == null) {
            throw new RuntimeException("El rol de estudiante no existe.");
        }
        teacher.setRole_id(role);
        // Buscar la compañía basada en el email proporcionado
        Company company = companyRepository.findByEmail(companyEmail);
        if (company == null) {
        throw new RuntimeException("No se encontró una compañía con el email proporcionado.");
        }
        // Asignar la entidad Company al estudiante
        teacher.setCompany_id(company);

        return teacherRepository.save(teacher);
    }

    // Obtener los datos de un estudiante por ID
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + id));
    }

    // Obtener todos los estudiantes
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // Obtener el profesor asignado a un estudiante
    // public Teacher getAssignedTeacher(Long teacherId) {
    //     Teacher teacher = teacherRepository.findById(teacherId)
    //             .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + teacherId));

    //     Long companyId = teacher.getCompany_id().getId();
    //     Optional<Teacher> assignedTeacher = teacherRepository.findByCompanyId(companyId);

    //     return assignedTeacher.orElseThrow(() -> new ResourceNotFoundException("No teacher assigned for teacher ID: " + teacherId));
    // }

    // Actualizar algunos datos del estudiante
    public Teacher updateTeacherPartial(Long id, Teacher teacherDetails) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + id));

        if (teacherDetails.getFirst_name() != null) {
            existingTeacher.setFirst_name(teacherDetails.getFirst_name());
        }
        if (teacherDetails.getLast_name() != null) {
            existingTeacher.setLast_name(teacherDetails.getLast_name());
        }
        if (teacherDetails.getEmail() != null) {
            existingTeacher.setEmail(teacherDetails.getEmail());
        }
        if (teacherDetails.getPhone() != null) {
            existingTeacher.setPhone(teacherDetails.getPhone());
        }
        if (teacherDetails.getPassword() != null) {
            existingTeacher.setPassword(teacherDetails.getPassword());
        }

        return teacherRepository.save(existingTeacher);
    }

    // Eliminar un estudiante por ID
    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Teacher not found with ID: " + id);
        }
        teacherRepository.deleteById(id);
    }
}
