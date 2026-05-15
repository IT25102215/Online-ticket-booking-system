package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // CREATE - Save a new event
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    // READ - Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // READ - Get a single event by ID
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    // READ - Search events by name keyword
    public List<Event> searchEventsByName(String keyword) {
        return eventRepository.findByNameContainingIgnoreCase(keyword);
    }

    // READ - Filter events by category
    public List<Event> getEventsByCategory(String category) {
        return eventRepository.findByCategory(category);
    }

    // UPDATE - Update an existing event (uses save with existing ID)
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    // DELETE - Delete an event by ID
    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }
}