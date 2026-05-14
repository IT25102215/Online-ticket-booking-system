// src/main/java/com/example/demo/repository/VenueRepository.java

package com.example.demo.repository;

import com.example.demo.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {

    // Find all venues belonging to a specific organizer
    List<Venue> findByOrganizerId(Long organizerId);

    // Find venues in a specific city
    List<Venue> findByCityIgnoreCase(String city);

    // Find venues with capacity greater than or equal to a given value
    List<Venue> findByCapacityGreaterThanEqual(Integer capacity);
}

