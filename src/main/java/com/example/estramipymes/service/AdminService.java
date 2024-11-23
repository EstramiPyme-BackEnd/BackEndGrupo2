package com.example.estramipymes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estramipymes.model.Admin;
import com.example.estramipymes.repository.AdminRepository;
import com.example.estramipymes.util.EncryptionUtil;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Crear Admin
    public Admin createAdmin(Admin admin) {

        try {
            admin.setPassword(EncryptionUtil.encrypt(admin.getPassword())); // Encriptar contraseña
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting password", e);
        }
        return adminRepository.save(admin);
    }

    // Obtener Admin por ID
    public Admin getAdminById(Long id) {
        Admin admin = adminRepository.findById(id).orElse(null);
        if (admin != null) {
            try {
                admin.setPassword(admin.getDecryptedPassword());
            } catch (Exception ignored) {
            }
        }
        return admin;
    }

    // Actualizar Admin por ID
    public Admin updateAdmin(Long id, Admin admin) {

        Admin existingAdmin = adminRepository.findById(id).orElse(null);

        if (existingAdmin == null)
        return null;

        existingAdmin.setEmail(admin.getEmail() == null ? existingAdmin.getEmail() : admin.getEmail());
        if (admin.getPassword() != null) {
            try {
                existingAdmin.setPassword(EncryptionUtil.encrypt(admin.getPassword()));
            } catch (Exception e) {
                throw new RuntimeException("Error encrypting password", e);
            }
        }

        return adminRepository.save(existingAdmin);
    }

    // Eliminar Admin por ID
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
    // Obtener lista de Admins
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
    
}
