package com.example.demo.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Abstract method for Polymorphism
    public abstract String getDetails();

    // Encapsulation
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}