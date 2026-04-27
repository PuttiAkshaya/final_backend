package com.klu.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private User visitor;

    @ManyToOne
    @JoinColumn(name = "artwork_id")
    private Artwork artwork;

    private LocalDateTime orderDate;

    private String status; // PENDING, COMPLETED, CANCELLED

    // Constructors
    public Order() {}
    public Order(Long id, User visitor, Artwork artwork, LocalDateTime orderDate, String status) {
        this.id = id;
        this.visitor = visitor;
        this.artwork = artwork;
        this.orderDate = orderDate;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getVisitor() { return visitor; }
    public void setVisitor(User visitor) { this.visitor = visitor; }
    public Artwork getArtwork() { return artwork; }
    public void setArtwork(Artwork artwork) { this.artwork = artwork; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
