package com.example.demo.admin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String adminRole;

    // Default Constructor
    public Admin() {
        super();
    }

    // Parameterized Constructor
    public Admin(String name, String username, String email, String adminRole) {
        super(name);
        this.username = username;
        this.email = email;
        this.adminRole = adminRole;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }
}