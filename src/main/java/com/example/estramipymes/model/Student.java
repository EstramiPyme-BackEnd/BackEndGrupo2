package com.example.estramipymes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data; 
      
@Entity
@Data
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long  id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String first_name;

    @Column(name = "last_name", length = 50, nullable = false)
    private String last_name;

    @Column(name = "email", length = 25, nullable = false)
    private String email;

    @Column(name = "password", length = 25, nullable = false)
    private String password;

    @Column(name = "phone", length = 50, nullable = false)
    private String phone;

    @Column(name = "role_id")
    private Long role_id;

    @Column(name = "company_id")
    private Long company_id;

}
