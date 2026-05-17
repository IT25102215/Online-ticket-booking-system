package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.TicketOrder;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Service
public class FileService {

    // Existing method for events
    public void logEventCreation(Event event) {
        try (FileWriter fw = new FileWriter("event_logs.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println("NEW EVENT CREATED: " + event.getName() + " | Date: " + event.getEventDate() + " | Price: LKR " + event.getTicketPrice());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the event file.");
        }
    }

    // NEW method for ticket receipts
    public void logTicketPurchase(TicketOrder order) {
        try (FileWriter fw = new FileWriter("ticket_receipts.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println("--- TICKET RECEIPT ---");
            pw.println("Date/Time: " + LocalDateTime.now());
            pw.println("Customer: " + order.getCustomerName());
            pw.println("Event: " + order.getEvent().getName());
            pw.println("Quantity: " + order.getQuantity());
            pw.println("Total Paid: LKR " + order.getTotalPrice());
            pw.println("----------------------");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the ticket receipt.");
        }
    }
}