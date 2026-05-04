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

    // The Main Storefront
    @GetMapping("/")
    public String showMarketplace(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "index";
    }

    // The Event Details / Booking Page
    @GetMapping("/book/{id}")
    public String viewEventDetails(@PathVariable("id") Long id, Model model) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id: " + id));
        model.addAttribute("event", event);
        return "book_event";
    }

    // NEW POST ROUTE TO HANDLE BOOKINGS
    @PostMapping("/book/confirm")
    public String confirmBooking(
            @RequestParam("eventId") Long eventId,
            @RequestParam("customerName") String customerName,
            @RequestParam("quantity") int quantity) {

        // 1. Find the event in the database
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id"));

        // 2. Create the order and calculate the total price
        TicketOrder order = new TicketOrder();
        order.setCustomerName(customerName);
        order.setQuantity(quantity);
        order.setEvent(event);
        order.setTotalPrice(event.getTicketPrice() * quantity); // The math logic

        // 3. Save to MySQL
        ticketOrderRepository.save(order);

        // 4. Save to the Text File (File Handling Marks)
        fileService.logTicketPurchase(order);

        // 5. Send them back to the homepage after successful booking
        return "redirect:/?success=true";
    }
    // Make sure your repository is wired up at the top of your controller:
    // @Autowired
    // private TicketOrderRepository ticketOrderRepository;

    // --- 1. CREATE (Book a Ticket) ---
    @PostMapping("/book-ticket")
    public String bookTicket(@ModelAttribute TicketOrder ticketOrder) {
        // Saves the new ticket to the MySQL database
        ticketOrderRepository.save(ticketOrder);
        return "redirect:/my-tickets"; // Sends user straight to their history page
    }

    // --- 2. READ (View My Tickets History) ---
    @GetMapping("/my-tickets")
    public String viewMyTickets(Model model) {
        // Fetches all tickets from the database and sends them to the HTML
        model.addAttribute("tickets", ticketOrderRepository.findAll());
        return "my_tickets";
    }

    // --- 3. DELETE (Cancel a Booking) ---
    @PostMapping("/cancel-ticket/{id}")
    public String cancelTicket(@PathVariable Long id) {
        // Deletes the specific ticket from the database using its ID
        ticketOrderRepository.deleteById(id);
        return "redirect:/my-tickets"; // Refreshes the page to show it's gone
    }

}
