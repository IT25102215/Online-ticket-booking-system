package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.model.TicketOrder;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.TicketOrderRepository;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketOrderRepository ticketOrderRepository;

    @Autowired
    private FileService fileService;

    // --- 1. The Main Storefront ---
    @GetMapping("/")
    public String showMarketplace(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "index";
    }

    // --- 2. The Event Details / Booking Page ---
    @GetMapping("/book/{id}")
    public String viewEventDetails(@PathVariable("id") Long id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id: " + id));
        model.addAttribute("event", event);
        return "book_event";
    }

    // --- 3. Handle Bookings (MySQL + Text File) ---
    @PostMapping("/book/confirm")
    public String confirmBooking(
            @RequestParam("eventId") Long eventId,
            @RequestParam("customerName") String customerName,
            @RequestParam("quantity") int quantity) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id"));

        TicketOrder order = new TicketOrder();
        order.setCustomerName(customerName);
        order.setQuantity(quantity);
        order.setEvent(event);
        order.setTotalPrice(event.getTicketPrice() * quantity);

        ticketOrderRepository.save(order);
        fileService.logTicketPurchase(order);

        return "redirect:/?success=true";
    }

    // --- 4. View My Tickets History ---
    @GetMapping("/my-tickets")
    public String viewMyTickets(Model model) {
        model.addAttribute("tickets", ticketOrderRepository.findAll());
        return "my_tickets";
    }

    // --- 5. Cancel a Booking ---
    @PostMapping("/cancel-ticket/{id}")
    public String cancelTicket(@PathVariable Long id) {
        ticketOrderRepository.deleteById(id);
        return "redirect:/my-tickets";
    }
}