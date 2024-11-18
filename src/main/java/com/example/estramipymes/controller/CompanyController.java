package com.example.estramipymes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.estramipymes.model.Company;
// import com.example.estramipymes.service.CompanyService;
import com.example.estramipymes.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // Ver todas las empresas (para admin y profesor)
    @GetMapping("")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    // Ver sus datos
    @GetMapping("/{id}")
    public Long getCompany(@PathVariable Long id) {
        return id;
    }

    // Crear todos sus datos
    @PostMapping()
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        // Company newCompany = companyService.createCompany(company);
        // if (company == null)
        //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // return new ResponseEntity<>(newCompany, HttpStatus.OK);
        
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    // Actualizar TODOS sus datos
    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody Company company) {
        return company;
    }

    // Eliminar TODOS sus datos
    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable Long id) {
        return "Se ha eliminado la empresa";
    }
    
    // Ver su profesor asignado

    // Ver su estudiante asignado
    
}
