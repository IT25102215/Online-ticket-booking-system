package com.example.demo.controller;

import com.example.demo.model.Organizer;
import com.example.demo.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizers")
@CrossOrigin(origins = "*")   // Frontend connect
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;

    // Create
    @PostMapping
    public ResponseEntity<Organizer> createOrganizer(@RequestBody Organizer organizer) {
        Organizer saved = organizerService.saveOrganizer(organizer);
        return ResponseEntity.ok(saved);
    }

    // Read All
    @GetMapping
    public List<Organizer> getAllOrganizers() {
        return organizerService.getAllOrganizers();
    }

    // Read By ID
    @GetMapping("/{id}")
    public ResponseEntity<Organizer> getOrganizerById(@PathVariable Long id) {
        return organizerService.getOrganizerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Organizer> updateOrganizer(@PathVariable Long id,
                                                     @RequestBody Organizer organizer) {
        Organizer updated = organizerService.updateOrganizer(id, organizer);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganizer(@PathVariable Long id) {
        organizerService.deleteOrganizer(id);
        return ResponseEntity.noContent().build();
    }

    // Search
    @GetMapping("/search")
    public List<Organizer> searchOrganizers(@RequestParam String name) {
        return organizerService.searchOrganizers(name);
    }
}