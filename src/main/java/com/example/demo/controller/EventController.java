package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    // READ: Display all events
    @GetMapping
    public String listEvents(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "events";
    }

    // CREATE: Show form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("event", new Event());
        return "create_event";
    }

    // CREATE/UPDATE: Save to Database
    @PostMapping("/save")
    public String saveEvent(@ModelAttribute("event") Event event) {
        eventRepository.save(event);
        return "redirect:/events";
    }

    // UPDATE: Show form with existing data
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id:" + id));
        model.addAttribute("event", event);
        return "edit_event";
    }

    // DELETE: Remove an event
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable("id") Long id) {
        eventRepository.deleteById(id);
        return "redirect:/events";
    }
    // View for the public marketplace storefront
    @GetMapping("/")
    public String showMarketplace(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "index"; // This will map to a new index.html file
    }

}