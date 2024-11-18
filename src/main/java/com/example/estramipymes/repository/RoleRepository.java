package com.example.estramipymes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.estramipymes.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByDescription(Role.Description description);
}
