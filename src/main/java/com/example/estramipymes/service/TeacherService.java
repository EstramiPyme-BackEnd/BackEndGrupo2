package com.example.estramipymes.service;

import java.util.List;

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

    // @Autowired
    // private TeacherRepository teacherRepository;

    // Crear un estudiante
    public Teacher createTeacher(Teacher teacher) {
        Teacher existingTeacher = teacherRepository.findByEmail(teacher.getEmail());
        if (existingTeacher != null)
            return null;

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

    // Actualizar algunos datos del estudiante
    public Teacher updateTeacherPartial(Long id, Teacher teacherDetails) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + id));

        if (teacherDetails.getName() != null) {
            existingTeacher.setName(teacherDetails.getName());
        }
        if (teacherDetails.getEmail() != null) {
            existingTeacher.setEmail(teacherDetails.getEmail());
        }
        if (teacherDetails.getProfesorParteProyecto() != null) {
            existingTeacher.setProfesorParteProyecto(teacherDetails.getProfesorParteProyecto());
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
