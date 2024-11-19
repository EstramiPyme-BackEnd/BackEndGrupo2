package com.example.estramipymes.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.estramipymes.model.Company;
import com.example.estramipymes.service.CompanyService;


@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // Lists all the companies (this method is aimed to be used by teachers and admins only)
    @GetMapping("")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    // Lists a company depending on its id (It is aimed to be available for each company and 
    // show only results associated to their own id)
    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Long id) {
        return companyService.getCompany(id);
    }

    // Creates all the data of a company entity (This expects to receive only name, email and password
    // the id is auto generated and role_id is set to company)
    @PostMapping()
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company newCompany = companyService.createCompany(company);

        if (newCompany == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(newCompany, HttpStatus.OK);
        
    }

    // Updates any or some data from a company entity
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        
        Company updatedCompany = companyService.updateCompany(id, company);

        if (updatedCompany == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    // Deletes a whole register of a selected entity (This should work in the front end in any
    // company profile with its id fixed, and for admin, any id available to select)
    @DeleteMapping("/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable Long id) {

        companyService.deleteCompany(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    // Ver su profesor asignado

    // Ver su estudiante asignado
    
}
