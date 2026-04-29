package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private int quantity;
    private double totalPrice;

    // Many tickets can be booked for One Event
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    public TicketOrder() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
}