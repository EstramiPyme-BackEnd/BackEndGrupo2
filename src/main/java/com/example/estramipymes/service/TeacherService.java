package com.example.estramipymes.service;

import java.util.List;

import com.example.estramipymes.model.Teacher;
import com.example.estramipymes.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estramipymes.exception.ResourceNotFoundException;
import com.example.estramipymes.model.Teacher;
import com.example.estramipymes.repository.TeacherRepository;
//import com.example.estramipymes.repository.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    

    // Crear un proesor
    public Teacher createTeacher(Teacher teacher) {
        return TeacherRepository.save(teacher);
    }

    // Obtener los datos de un estudiante por ID
    public Teacher getTeacherById(Long id) {
        return TeacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + id));
    }

    // Obtener todos los profesores
    public List<Teacher> getAllTeachers() {
        return TeacherRepository.findAll();
    }

    // Obtener el profesor asignado a un estudiante
    // public Teacher getAssignedTeacher(Long TeacherId) {
    //     Teacher Teacher = TeacherRepository.findById(TeacherId)
    //             .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + TeacherId));

    //     Long companyId = Teacher.getCompany_id().getId();
    //     Optional<Teacher> assignedTeacher = teacherRepository.findByCompanyId(companyId);

    //     return assignedTeacher.orElseThrow(() -> new ResourceNotFoundException("No teacher assigned for Teacher ID: " + TeacherId));
    // }

    // Actualizar algunos datos del estudiante
    public Teacher updateTeacherPartial(Long id, Teacher TeacherDetails) {
        Teacher existingTeacher = TeacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + id));

        if (TeacherDetails.getFirst_name() != null) {
            existingTeacher.setFirst_name(TeacherDetails.getFirst_name());
        }
        if (TeacherDetails.getLast_name() != null) {
            existingTeacher.setLast_name(TeacherDetails.getLast_name());
        }
        if (TeacherDetails.getEmail() != null) {
            existingTeacher.setEmail(TeacherDetails.getEmail());
        }
        if (TeacherDetails.getPhone() != null) {
            existingTeacher.setPhone(TeacherDetails.getPhone());
        }

        return TeacherRepository.save(existingTeacher);
    }

    // Eliminar un estudiante por ID
    public void deleteTeacher(Long id) {
        if (!TeacherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Teacher not found with ID: " + id);
        }
        TeacherRepository.deleteById(id);
    }

    public TeacherRepository getTeacherRepository() {
        return TeacherRepository;
    }

    public void setTeacherRepository(TeacherRepository teacherRepository) {
        TeacherRepository = teacherRepository;
    }
}
