package com.example.estramipymes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estramipymes.exception.ServiceResponse;
import com.example.estramipymes.model.Admin;
import com.example.estramipymes.repository.AdminRepository;
import com.example.estramipymes.util.EncryptionUtil;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

     // Obtener(GET) lista de Admins
     public List<Admin> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        admins.forEach(admin -> {
            try {
                admin.setPassword(admin.getDecryptedPassword());
            } catch (Exception ignored) {
            }
        });
        return admins;
    }

     // Obtener (GET)Admin por ID
     public Admin getAdmin(Long id) {
        Admin admin = adminRepository.findById(id).orElse(null);
        if (admin != null) {
            try {
                admin.setPassword(admin.getDecryptedPassword());
            } catch (Exception ignored) {
            }
        }
        return admin;
    }
    // Obtener(GET) Admin por email
    public Admin getAdminByEmail(String email) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null) {
            try {
                admin.setPassword(admin.getDecryptedPassword());
            } catch (Exception ignored) {
            }
        }
        return admin;
    }

    // Crear(POST) Admin
    public ServiceResponse<Admin> createAdmin(Admin admin) {
        Admin existingAdmin = adminRepository.findByEmail(admin.getEmail());
        if (existingAdmin != null) {
            return ServiceResponse.error("El correo electrónico '" + admin.getEmail() + "' ya está registrado");
        }

        try {
            admin.setPassword(EncryptionUtil.encrypt(admin.getPassword()));
        } catch (Exception e) {
            return ServiceResponse.error("Error al encriptar la contraseña");
        }

        Admin savedAdmin = adminRepository.save(admin);
        return ServiceResponse.success(savedAdmin);
    }

    // Actualizar(PUT) Admin por ID
    public ServiceResponse<Admin> updateAdmin(Long id, Admin admin) {
        Admin existingAdmin = adminRepository.findById(id).orElse(null);

        if (existingAdmin == null) {
            return ServiceResponse.error("Usuario no encontrado");
        }

        if (admin.getEmail() != null && !admin.getEmail().equals(existingAdmin.getEmail())) {
            Admin adminWithNewEmail = adminRepository.findByEmail(admin.getEmail());
            if (adminWithNewEmail != null) {
                return ServiceResponse.error("El correo electrónico '" + admin.getEmail() + "' ya está en uso por otro usuario");
            }
        }

        existingAdmin.setEmail(admin.getEmail() == null ? existingAdmin.getEmail() : admin.getEmail());
   

        if (admin.getPassword() != null) {
            try {
                existingAdmin.setPassword(EncryptionUtil.encrypt(admin.getPassword()));
            } catch (Exception e) {
                return ServiceResponse.error("Error al encriptar la contraseña");
            }
        }

        Admin savedAdmin = adminRepository.save(existingAdmin);
        return ServiceResponse.success(savedAdmin);
    }

    // Eliminar(DELETE) Admin por ID
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
