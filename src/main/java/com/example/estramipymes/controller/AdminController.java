package com.example.estramipymes.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.estramipymes.dto.ApiResponse;
import com.example.estramipymes.exception.ServiceResponse;
import com.example.estramipymes.model.Admin;
import com.example.estramipymes.service.AdminService;


@RestController
@RequestMapping("/admin")  
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Crear un nuevo admin
    @PostMapping()
    public ResponseEntity<?> createAdmin(@RequestBody Admin admin) {
        ServiceResponse<Admin> response = adminService.createAdmin(admin);
        
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(response.getMessage()));
        }
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(response.getData());
    }


    // Ver datos de un admin por su ID
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdmin(@PathVariable Long id) {
        Admin admin = adminService.getAdmin(id);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Usuario no encontrado"));
        }
        return ResponseEntity.ok(admin);
    }

    // Actualizar datos de un admin por su ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        ServiceResponse<Admin> response = adminService.updateAdmin(id, admin);
        
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(response.getMessage()));
        }
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(response.getData());
    }

    // Eliminar un admin por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener la lista de admins
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }
    @GetMapping("/")
    public ResponseEntity<?> getAdminByEmail(@RequestParam(required = false) String email) {       
        Admin admin = adminService.getAdminByEmail(email);
        if (admin != null) {
            return ResponseEntity.ok(Collections.singletonList(admin));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Usuario no encontrado"));
        }
    }
}
