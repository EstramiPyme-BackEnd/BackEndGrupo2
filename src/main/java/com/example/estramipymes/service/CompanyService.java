package com.example.estramipymes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estramipymes.model.Company;
import com.example.estramipymes.model.Role;
import com.example.estramipymes.repository.CompanyRepository;
import com.example.estramipymes.repository.RoleRepository;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company createCompany(Company company) {
        Company existingCompany = companyRepository.findByEmail(company.getEmail());
        if (existingCompany != null)
            return null;

        Role role = roleRepository.findById(company.getRole_id().getId()).orElse(null);

        company.setRole_id(role);

        return companyRepository.save(company);
        // Falta terminar esto
    }
    
}
