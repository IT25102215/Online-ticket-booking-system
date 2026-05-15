package com.example.demo.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {
    private String name;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}