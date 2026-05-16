package com.example.demo.service;

import com.example.demo.model.Organizer;
import com.example.demo.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    // Create
    public Organizer saveOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    // Read All
    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    // Read By ID
    public Optional<Organizer> getOrganizerById(Long id) {
        return organizerRepository.findById(id);
    }

    // Update
    public Organizer updateOrganizer(Long id, Organizer updatedOrganizer) {
        updatedOrganizer.setId(id);
        return organizerRepository.save(updatedOrganizer);
    }

    // Delete
    public void deleteOrganizer(Long id) {
        organizerRepository.deleteById(id);
    }

    // Search
    public List<Organizer> searchOrganizers(String name) {
        return organizerRepository.findByNameContainingIgnoreCase(name);
    }
}