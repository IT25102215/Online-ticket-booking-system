package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // ──────────────────────────────────────────────────────────────────────────
    // READ – List all events (main page)
    // URL: GET /events
    // ──────────────────────────────────────────────────────────────────────────
    @GetMapping
    public String listEvents(@RequestParam(value = "search", required = false) String search,
                             @RequestParam(value = "category", required = false) String category,
                             Model model) {

        List<Event> events;

        if (search != null && !search.isBlank()) {
            events = eventService.searchEventsByName(search);
            model.addAttribute("search", search);
        } else if (category != null && !category.isBlank()) {
            events = eventService.getEventsByCategory(category);
            model.addAttribute("selectedCategory", category);
        } else {
            events = eventService.getAllEvents();
        }

        model.addAttribute("events", events);
        model.addAttribute("newEvent", new Event()); // blank form object for Create
        return "events"; // → templates/events.html
    }

    // ──────────────────────────────────────────────────────────────────────────
    // CREATE – Save a new event
    // URL: POST /events/save
    // ──────────────────────────────────────────────────────────────────────────
    @PostMapping("/save")
    public String saveEvent(@ModelAttribute("newEvent") Event event) {
        eventService.saveEvent(event);
        return "redirect:/events";
    }

    // ──────────────────────────────────────────────────────────────────────────
    // UPDATE – Show pre-filled edit form
    // URL: GET /events/edit/{id}
    // ──────────────────────────────────────────────────────────────────────────
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Event> eventOpt = eventService.getEventById(id);
        if (eventOpt.isEmpty()) {
            return "redirect:/events";
        }
        model.addAttribute("editEvent", eventOpt.get());
        model.addAttribute("events", eventService.getAllEvents());
        model.addAttribute("newEvent", new Event());
        return "events";
    }

    // ──────────────────────────────────────────────────────────────────────────
    // UPDATE – Save updated event
    // URL: POST /events/update
    // ──────────────────────────────────────────────────────────────────────────
    @PostMapping("/update")
    public String updateEvent(@ModelAttribute("editEvent") Event event) {
        eventService.updateEvent(event);
        return "redirect:/events";
    }

    // ──────────────────────────────────────────────────────────────────────────
    // DELETE – Remove an event
    // URL: POST /events/delete/{id}
    // ──────────────────────────────────────────────────────────────────────────
    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return "redirect:/events";
    }
}