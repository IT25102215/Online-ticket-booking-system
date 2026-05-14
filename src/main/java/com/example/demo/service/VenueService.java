// src/main/java/com/example/demo/service/VenueService.java

package com.example.demo.service;

import com.example.demo.model.Venue;
import com.example.demo.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    @Autowired
    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    // CREATE or UPDATE a venue
    public Venue save(Venue venue) {
        return venueRepository.save(venue);
    }

    // READ - Get all venues
    public List<Venue> findAll() {
        return venueRepository.findAll();
    }

    // READ - Get a single venue by ID
    public Optional<Venue> findById(Long id) {
        return venueRepository.findById(id);
    }

    // DELETE a venue by ID
    public void deleteById(Long id) {
        venueRepository.deleteById(id);
    }

    // Get all venues for a specific organizer
    public List<Venue> findByOrganizerId(Long organizerId) {
        return venueRepository.findByOrganizerId(organizerId);
    }

    // Check if venue exists
    public boolean existsById(Long id) {
        return venueRepository.existsById(id);
    }
}
