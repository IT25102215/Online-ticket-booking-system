// src/main/java/com/example/demo/controller/OrganizerController.java

package com.example.demo.controller;

import com.example.demo.model.Organizer;
import com.example.demo.service.OrganizerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/organizers")
public class OrganizerController {

    private final OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    // ─────────────────────────────────────────────
    // READ: Show all organizers + blank form to add new
    // GET /organizers
    // ─────────────────────────────────────────────
    @GetMapping
    public String listOrganizers(Model model) {
        model.addAttribute("organizers", organizerService.findAll());
        model.addAttribute("organizer", new Organizer()); // blank form object
        model.addAttribute("pageTitle", "Organizer Management");
        return "organizers"; // resolves to templates/organizers.html
    }

    // ─────────────────────────────────────────────
    // READ: Load organizer data into form for editing
    // GET /organizers/edit/{id}
    // ─────────────────────────────────────────────
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model,
                               RedirectAttributes redirectAttributes) {
        Organizer organizer = organizerService.findById(id)
                .orElse(null);

        if (organizer == null) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Organizer with ID " + id + " not found.");
            return "redirect:/organizers";
        }

        model.addAttribute("organizers", organizerService.findAll());
        model.addAttribute("organizer", organizer); // pre-filled form object
        model.addAttribute("pageTitle", "Organizer Management");
        model.addAttribute("editMode", true);
        return "organizers";
    }

    // ─────────────────────────────────────────────
    // CREATE: Handle new organizer form submission
    // POST /organizers/save
    // ─────────────────────────────────────────────
    @PostMapping("/save")
    public String saveOrganizer(@Valid @ModelAttribute("organizer") Organizer organizer,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            // Return to page with validation errors
            model.addAttribute("organizers", organizerService.findAll());
            model.addAttribute("pageTitle", "Organizer Management");
            if (organizer.getId() != null) {
                model.addAttribute("editMode", true);
            }
            return "organizers";
        }

        organizerService.save(organizer);

        String message = (organizer.getId() == null)
                ? "Organizer created successfully!"
                : "Organizer updated successfully!";
        redirectAttributes.addFlashAttribute("successMessage", message);
        return "redirect:/organizers";
    }

    // ─────────────────────────────────────────────
    // DELETE: Remove an organizer by ID
    // POST /organizers/delete/{id}
    // ─────────────────────────────────────────────
    @PostMapping("/delete/{id}")
    public String deleteOrganizer(@PathVariable Long id,
                                  RedirectAttributes redirectAttributes) {
        if (!organizerService.existsById(id)) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Organizer not found. Cannot delete.");
            return "redirect:/organizers";
        }

        organizerService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage",
                "Organizer deleted successfully.");
        return "redirect:/organizers";
    }
}

