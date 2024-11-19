package com.example.estramipymes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estramipymes.model.Role;
import com.example.estramipymes.repository.RoleRepository;

@Service
public class RoleService {

@Autowired 
private RoleRepository roleRepository;

// Read (GET) all the roles
public List<Role> getRoles() {
    return roleRepository.findAll();
}

// Read (GET) a role by ID
public Role getRole(Long id) {
    return roleRepository.findById(id).orElse(null);
}

// Create (POST) a new role
public Role createRole(Role role) {
    Role existingRole = roleRepository.findByDescription(role.getDescription());
    if (existingRole != null)
        return null;

    return roleRepository.save(role);
}

// Remove (DELETE) an existing role
public void deleteRole(Long id) {

    roleRepository.deleteById(id);
}

// Update (PUT) a role description
public Role updateRole(Long id, Role role) {

    Role existingRole = roleRepository.findById(id).orElse(null);

    if (existingRole == null)
    return null;

    existingRole.setDescription(role.getDescription() == null ? existingRole.getDescription() : role.getDescription());
    
    return roleRepository.save(existingRole);
}
}

    