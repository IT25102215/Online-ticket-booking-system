package com.example.demo.repository;

import com.example.demo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Find events by category (for search/filter)
    List<Event> findByCategory(String category);

    // Find events by name containing a keyword (case-insensitive search)
    List<Event> findByNameContainingIgnoreCase(String name);
}