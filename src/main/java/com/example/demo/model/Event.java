package com.example.demo.model;

import jakarta.persistence.Entity;

@Entity
public class Event extends BookingEntity {
    private String eventName;
    private String eventDate;
    private double ticketPrice;

    // New fields for
    private String category; // e.g., "Movie", "Concert", "Drama"
    private String description;
    private String imageUrl;

    public Event() {}

    @Override
    public String getDetails() {
        return category + ": " + this.eventName + " on " + this.eventDate;
    }

    // Existing Getters and Setters
    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }
    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }
    public double getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(double ticketPrice) { this.ticketPrice = ticketPrice; }

    // New Getters and Setters
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}