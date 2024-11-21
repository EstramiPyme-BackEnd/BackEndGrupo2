// package com.example.estramipymes.controller;

// import com.example.estramipymes.model.Admin;
// import com.example.estramipymes.service.AdminService;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/admin")  
// public class AdminController {

//     @Autowired
//     private AdminService adminService;

//     // Crear un nuevo admin
//     @PostMapping
//     public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
//         Admin createdAdmin = adminService.createAdmin(admin);
//         return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
//     }

//     // Ver datos de un admin por su ID
//     @GetMapping("/{id}")
//     public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
//         Admin admin = adminService.getAdminById(id);
//         return admin != null ? ResponseEntity.ok(admin) : ResponseEntity.notFound().build();
//     }

//     // Actualizar datos de un admin por su ID
//     @PutMapping("/{id}")
//     public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
//         Admin updatedAdmin = adminService.updateAdmin(id, admin);
//         return updatedAdmin != null ? ResponseEntity.ok(updatedAdmin) : ResponseEntity.notFound().build();
//     }

//     // Eliminar un admin por su ID
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
//         adminService.deleteAdmin(id);
//         return ResponseEntity.noContent().build();
//     }

//     // Obtener la lista de admins
//     @GetMapping
//     public ResponseEntity<List<Admin>> getAllAdmins() {
//         return ResponseEntity.ok(adminService.getAllAdmins());
//     }
// }
