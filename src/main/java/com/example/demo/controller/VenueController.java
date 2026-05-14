// src/main/java/com/example/demo/controller/VenueController.java

package com.example.demo.controller;

import com.example.demo.model.Venue;
import com.example.demo.service.OrganizerService;
import com.example.demo.service.VenueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/venues")
public class VenueController {

    private final VenueService venueService;
    private final OrganizerService organizerService;

    @Autowired
    public VenueController(VenueService venueService, OrganizerService organizerService) {
        this.venueService = venueService;
        this.organizerService = organizerService;
    }

    // ─────────────────────────────────────────────
    // READ: Show all venues + blank form
    // GET /venues
    // ─────────────────────────────────────────────
    @GetMapping
    public String listVenues(Model model) {
        model.addAttribute("venues", venueService.findAll());
        model.addAttribute("venue", new Venue()); // blank form object
        model.addAttribute("organizers", organizerService.findAll()); // for dropdown
        model.addAttribute("pageTitle", "Venue Management");
        return "venues"; // resolves to templates/venues.html
    }

    // ─────────────────────────────────────────────
    // READ: Load venue data into form for editing
    // GET /venues/edit/{id}
    // ─────────────────────────────────────────────
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model,
                               RedirectAttributes redirectAttributes) {
        Venue venue = venueService.findById(id).orElse(null);

        if (venue == null) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Venue with ID " + id + " not found.");
            return "redirect:/venues";
        }

        model.addAttribute("venues", venueService.findAll());
        model.addAttribute("venue", venue); // pre-filled form object
        model.addAttribute("organizers", organizerService.findAll());
        model.addAttribute("pageTitle", "Venue Management");
        model.addAttribute("editMode", true);
        return "venues";
    }

    // ─────────────────────────────────────────────
    // CREATE / UPDATE: Handle venue form submission
    // POST /venues/save
    // ─────────────────────────────────────────────
    @PostMapping("/save")
    public String saveVenue(@Valid @ModelAttribute("venue") Venue venue,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("venues", venueService.findAll());
            model.addAttribute("organizers", organizerService.findAll());
            model.addAttribute("pageTitle", "Venue Management");
            if (venue.getId() != null) {
                model.addAttribute("editMode", true);
            }
            return "venues";
        }

        venueService.save(venue);

        String message = (venue.getId() == null)
                ? "Venue created successfully!"
                : "Venue updated successfully!";
        redirectAttributes.addFlashAttribute("successMessage", message);
        return "redirect:/venues";
    }

    // ─────────────────────────────────────────────
    // DELETE: Remove a venue by ID
    // POST /venues/delete/{id}
    // ─────────────────────────────────────────────
    @PostMapping("/delete/{id}")
    public String deleteVenue(@PathVariable Long id,
                              RedirectAttributes redirectAttributes) {
        if (!venueService.existsById(id)) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Venue not found. Cannot delete.");
            return "redirect:/venues";
        }

        venueService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage",
                "Venue deleted successfully.");
        return "redirect:/venues";
    }
}
