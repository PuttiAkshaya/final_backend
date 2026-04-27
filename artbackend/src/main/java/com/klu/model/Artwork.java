package com.klu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "artworks")
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition = "LONGTEXT")
    private String imageUrl;

    private String category;

    @Column(columnDefinition = "TEXT")
    private String culturalHistory;

    private boolean approved = false;
    private boolean sold = false;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private User artist;

    // Default Constructor
    public Artwork() {}

    // All-args Constructor
    public Artwork(Long id, String title, String description, Double price, String imageUrl, String category, String culturalHistory, boolean approved, boolean sold, User artist) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
        this.culturalHistory = culturalHistory;
        this.approved = approved;
        this.sold = sold;
        this.artist = artist;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getCulturalHistory() { return culturalHistory; }
    public void setCulturalHistory(String culturalHistory) { this.culturalHistory = culturalHistory; }

    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }

    public boolean isSold() { return sold; }
    public void setSold(boolean sold) { this.sold = sold; }

    public User getArtist() { return artist; }
    public void setArtist(User artist) { this.artist = artist; }
}
