package com.example.estramipymes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Read (GET) all the companies
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Read (GET) a company by ID
    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    // Create (POST) a new company
    public Company createCompany(Company company) {
        System.out.println("Company object received: " + company);
        System.out.println("Company name: " + company.getName());
        companyRepository.findByEmail(company.getEmail())
                .ifPresent(existing -> {
                    throw new RuntimeException("A company with this email already exists.");
                });

        Role role = roleRepository.findByDescription(Role.Description.company)
                .orElseThrow(() -> new RuntimeException("Company role does not exist."));

        company.setRole_id(role);
        // Codificar la contraseña antes de guardarla
        String encodedPassword = passwordEncoder.encode(company.getPassword());
        company.setPassword(encodedPassword);
        System.out.println("Company name: " + company.getName());
        return companyRepository.save(company);
    }
    
    // Remove (DELETE) an existing company
    public void deleteCompany(Long id) {

        companyRepository.deleteById(id);
    }

    // Update (PUT) most of the data of a company entity (this applies both to company and admin)
    //  (Role_id can't be changed as company should always be a company)
    public Company updateCompany(Long id, Company company) {

        Company existingCompany = companyRepository.findById(id).orElse(null);

        if (existingCompany == null)
        return null;

        existingCompany.setName(company.getName() == null ? existingCompany.getName() : company.getName());
        existingCompany.setEmail(company.getEmail() == null ? existingCompany.getEmail() : company.getEmail());
        existingCompany.setPassword(company.getPassword() == null ? existingCompany.getPassword() : company.getPassword());


        return companyRepository.save(existingCompany);
    }
}
