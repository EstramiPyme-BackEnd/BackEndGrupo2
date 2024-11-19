package com.example.estramipymes.service;

import com.example.estramipymes.model.Admin;
// import com.example.estramipymes.model.Company;
// import com.example.estramipymes.model.Student;
// import com.example.estramipymes.model.Teacher;
import com.example.estramipymes.repository.AdminRepository;
// import com.example.estramipymes.repository.CompanyRepository;
// import com.example.estramipymes.repository.StudentRepository;
// import com.example.estramipymes.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // @Autowired
    // private TeacherRepository teacherRepository;

    // @Autowired
    // private StudentRepository studentRepository;

    // @Autowired
    // private CompanyRepository companyRepository;

    // Crear Admin
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Obtener Admin por ID
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin no encontrado con ID: " + id));
    }

    // Actualizar Admin por ID
    public Admin updateAdmin(Long id, Admin adminDetails) {
        Admin admin = getAdminById(id);
        admin.setName(adminDetails.getName());
        admin.setEmail(adminDetails.getEmail());
        admin.setPassword(adminDetails.getPassword());
        admin.setRole_id(adminDetails.getRole_id());
        return adminRepository.save(admin);
    }

    // Eliminar Admin por ID
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
    // Obtener lista de Admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
        }

    // // Crear entidad (Company, Teacher, Student)
    // public Object createEntity(String entity, Object object) {
    //     switch (entity.toLowerCase()) {
    //         case "teacher":
    //             return teacherRepository.save((Teacher) object);
    //         case "student":
    //             return studentRepository.save((Student) object);
    //         case "company":
    //             return companyRepository.save((Company) object);
    //         default:
    //             throw new IllegalArgumentException("Entidad no válida");
    //     }
    // }

    // // Obtener lista de Teachers
    // public List<Teacher> getAllTeachers() {
    //     return teacherRepository.findAll();
    // }

    // // Obtener lista de Students
    // public List<Student> getAllStudents() {
    //     return studentRepository.findAll();
    // }

    // // Obtener lista de Companies
    // public List<Company> getAllCompanies() {
    //     return companyRepository.findAll();
    // }

    // // Obtener cualquier entidad por ID
    // public Object getEntityById(String entity, Long id) {
    //     switch (entity.toLowerCase()) {
    //         case "teacher":
    //             return teacherRepository.findById(id)
    //                     .orElseThrow(() -> new ResourceNotFoundException("Teacher no encontrado con ID: " + id));
    //         case "student":
    //             return studentRepository.findById(id)
    //                     .orElseThrow(() -> new ResourceNotFoundException("Student no encontrado con ID: " + id));
    //         case "company":
    //             return companyRepository.findById(id)
    //                     .orElseThrow(() -> new ResourceNotFoundException("Company no encontrado con ID: " + id));
    //         default:
    //             throw new IllegalArgumentException("Entidad no válida");
    //     }
    // }

    // // Actualizar cualquier entidad por ID
    // @Transactional
    // public Object updateEntityById(String entity, Long id, Object object) {
    //     switch (entity.toLowerCase()) {
    //         case "teacher":
    //             Teacher teacher = teacherRepository.findById(id)
    //                     .orElseThrow(() -> new ResourceNotFoundException("Teacher no encontrado con ID: " + id));
    //             Teacher updatedTeacher = (Teacher) object;
    //             teacher.setName(updatedTeacher.getName());
    //             teacher.setEmail(updatedTeacher.getEmail());
    //             return teacherRepository.save(teacher);
    //         case "student":
    //             Student student = studentRepository.findById(id)
    //                     .orElseThrow(() -> new ResourceNotFoundException("Student no encontrado con ID: " + id));
    //             Student updatedStudent = (Student) object;
    //             student.setName(updatedStudent.getName());
    //             student.setEmail(updatedStudent.getEmail());
    //             return studentRepository.save(student);
    //         case "company":
    //             Company company = companyRepository.findById(id)
    //                     .orElseThrow(() -> new ResourceNotFoundException("Company no encontrado con ID: " + id));
    //             Company updatedCompany = (Company) object;
    //             company.setName(updatedCompany.getName());
    //             company.setEmail(updatedCompany.getEmail());
    //             return companyRepository.save(company);
    //         default:
    //             throw new IllegalArgumentException("Entidad no válida");
    //     }
    // }
    // // Asignar una empresa a un profesor
    // public Teacher assignCompanyToTeacher(Long teacherId, Long companyId) {
    //     Teacher teacher = teacherRepository.findById(teacherId)
    //             .orElseThrow(() -> new ResourceNotFoundException("Teacher no encontrado con ID: " + teacherId));
    //     Company company = companyRepository.findById(companyId)
    //             .orElseThrow(() -> new ResourceNotFoundException("Company no encontrado con ID: " + companyId));
    //     teacher.setCompany(company); // Asegúrate de que el campo 'company' exista en Teacher
    //     return teacherRepository.save(teacher);
    // }

    // // Asignar una empresa a un estudiante
    // public Student assignCompanyToStudent(Long studentId, Long companyId) {
    //     Student student = studentRepository.findById(studentId)
    //             .orElseThrow(() -> new ResourceNotFoundException("Student no encontrado con ID: " + studentId));
    //     Company company = companyRepository.findById(companyId)
    //             .orElseThrow(() -> new ResourceNotFoundException("Company no encontrado con ID: " + companyId));
    //     student.setCompany(company); // Asegúrate de que el campo 'company' exista en Student
    //     return studentRepository.save(student);
    // }

    // // Eliminar cualquier entidad por ID
    // public void deleteEntityById(String entity, Long id) {
    //     switch (entity.toLowerCase()) {
    //         case "teacher":
    //             teacherRepository.deleteById(id);
    //             break;
    //         case "student":
    //             studentRepository.deleteById(id);
    //             break;
    //         case "company":
    //             companyRepository.deleteById(id);
    //             break;
    //         default:
    //             throw new IllegalArgumentException("Entidad no válida");
    //     }
    // }

    
}
