package com.klu.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "exhibitions")
public class Exhibition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(
        name = "exhibition_artworks",
        joinColumns = @JoinColumn(name = "exhibition_id"),
        inverseJoinColumns = @JoinColumn(name = "artwork_id")
    )
    private List<Artwork> artworks;

    @ManyToOne
    @JoinColumn(name = "curator_id")
    private User curator;

    // Constructors
    public Exhibition() {}
    public Exhibition(Long id, String name, String description, List<Artwork> artworks, User curator) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.artworks = artworks;
        this.curator = curator;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Artwork> getArtworks() { return artworks; }
    public void setArtworks(List<Artwork> artworks) { this.artworks = artworks; }
    public User getCurator() { return curator; }
    public void setCurator(User curator) { this.curator = curator; }
}
