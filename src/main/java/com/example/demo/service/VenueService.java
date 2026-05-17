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

    // Update (Upgraded for Safety!)
    public Venue updateVenue(Long id, Venue updatedVenue) {
        // 1. Fetch the existing venue from the database
        Venue existingVenue = venueRepository.findById(id).orElse(null);

        if (existingVenue != null) {
            // 2. Set the ID to make sure we overwrite the correct row
            updatedVenue.setId(id);

            // 3. SAFETY CHECK: If the HTML form forgot the organizer, grab it from the old venue!
            if (updatedVenue.getOrganizer() == null) {
                updatedVenue.setOrganizer(existingVenue.getOrganizer());
            }

            // 4. Safely save
            return venueRepository.save(updatedVenue);
        }
        return null; // Return null if the venue didn't exist in the first place
    }

    // Delete
    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }
}