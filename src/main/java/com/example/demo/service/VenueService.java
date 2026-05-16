package com.example.demo.service;

import com.example.demo.model.Venue;
import com.example.demo.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;

    // Create
    public Venue saveVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    // Read All
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    // Read By ID
    public Optional<Venue> getVenueById(Long id) {
        return venueRepository.findById(id);
    }

    // Read Venues by Organizer
    public List<Venue> getVenuesByOrganizerId(Long organizerId) {
        return venueRepository.findByOrganizerId(organizerId);
    }

    // Update
    public Venue updateVenue(Long id, Venue updatedVenue) {
        updatedVenue.setId(id);
        return venueRepository.save(updatedVenue);
    }

    // Delete
    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }
}