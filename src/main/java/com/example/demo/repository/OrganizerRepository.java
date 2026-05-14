// src/main/java/com/example/demo/repository/OrganizerRepository.java

package com.example.demo.repository;

import com.example.demo.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

    // Find organizer by email (useful for validation)
    Optional<Organizer> findByEmail(String email);

    // Search organizers by name containing a keyword (case-insensitive)
    List<Organizer> findByNameContainingIgnoreCase(String name);
}

