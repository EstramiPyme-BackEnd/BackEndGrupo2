package com.example.estramipymes.model;

public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company_id; 

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
}