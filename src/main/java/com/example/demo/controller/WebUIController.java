package com.example.demo.controller;

import com.example.demo.service.OrganizerService;
import com.example.demo.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebUIController {

    @Autowired
    private VenueService venueService;

    @Autowired
    private OrganizerService organizerService;

    @GetMapping("/venues")
    public String showVenues(Model model) {
        model.addAttribute("listVenues", venueService.getAllVenues());
        return "venues";
    }

    @GetMapping("/organizers")
    public String showOrganizers(Model model) {
        model.addAttribute("listOrganizers", organizerService.getAllOrganizers());
        return "organizers";
    }
}