// src/main/java/com/example/demo/service/OrganizerService.java

package com.example.demo.service;

import com.example.demo.model.Organizer;
import com.example.demo.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizerService {

    private final OrganizerRepository organizerRepository;

    // Constructor injection (best practice over @Autowired on field)
    @Autowired
    public OrganizerService(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    // CREATE or UPDATE an organizer
    public Organizer save(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    // READ - Get all organizers
    public List<Organizer> findAll() {
        return organizerRepository.findAll();
    }

    // READ - Get a single organizer by ID
    public Optional<Organizer> findById(Long id) {
        return organizerRepository.findById(id);
    }

    // DELETE an organizer by ID
    public void deleteById(Long id) {
        organizerRepository.deleteById(id);
    }

    // Check if organizer exists by ID
    public boolean existsById(Long id) {
        return organizerRepository.existsById(id);
    }
}
