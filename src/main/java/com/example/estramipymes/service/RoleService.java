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

public List<Role> getRoles() {
    return roleRepository.findAll();
}


}
