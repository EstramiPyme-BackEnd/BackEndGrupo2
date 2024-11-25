package com.example.estramipymes.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.estramipymes.model.User;
import com.example.estramipymes.service.UserService;


@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    // Lists a user depending on its id (It is aimed to be available for each user and 
    // show only results associated to their own id)
    @GetMapping("/{id}")
    public ResponseEntity<?> getData(@PathVariable Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Usuario no encontrado"));
        }
        return ResponseEntity.ok(user);
    }
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/")
    public ResponseEntity<?> getDataByEmail(@RequestParam(required = false) String email) {       
        User user = userService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(Collections.singletonList(user));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Usuario no encontrado"));
        }
    }

    // Creates all the data of a user entity (This expects to receive only name, email and password
    // the id is auto generated and role_id is set to user)
    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody User user) {
        ServiceResponse<User> response = userService.createUser(user);
        
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(response.getMessage()));
        }
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(response.getData());
    }

    // Updates any or some data from a user entity
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        ServiceResponse<User> response = userService.updateUser(id, user);
        
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(response.getMessage()));
        }
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(response.getData());
    }

    // Deletes a whole register of a selected entity (This should work in the front end in any
    // user profile with its id fixed, and for admin, any id available to select)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    
}
