package com.example.estramipymes.service;

import com.example.estramipymes.model.Admin;
import com.example.estramipymes.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

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
    public Admin updateAdmin(Long id, Admin admin) {

        Admin existingAdmin = adminRepository.findById(id).orElse(null);

        if (existingAdmin == null)
        return null;

        existingAdmin.setEmail(admin.getEmail() == null ? existingAdmin.getEmail() : admin.getEmail());
        existingAdmin.setPassword(admin.getPassword() == null ? existingAdmin.getPassword() : admin.getPassword());

        return adminRepository.save(existingAdmin);
    }

    // Eliminar Admin por ID
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
    // Obtener lista de Admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
        }
    
}
