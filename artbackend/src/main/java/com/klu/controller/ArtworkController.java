package com.klu.controller;

import com.klu.model.Artwork;
import com.klu.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artworks")
@CrossOrigin(origins = "*")
public class ArtworkController {

    @Autowired
    private ArtworkRepository artworkRepository;

    @GetMapping
    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artwork> getArtworkById(@PathVariable Long id) {
        return artworkRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/artist/{artistId}")
    public List<Artwork> getArtworksByArtist(@PathVariable Long artistId) {
        return artworkRepository.findByArtistId(artistId);
    }

    @Autowired
    private com.klu.repository.UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createArtwork(@RequestBody Artwork artwork) {
        try {
            if (artwork.getArtist() != null && artwork.getArtist().getId() != null) {
                userRepository.findById(artwork.getArtist().getId()).ifPresent(artwork::setArtist);
            }
            Artwork saved = artworkRepository.save(artwork);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error saving artwork: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArtwork(@PathVariable Long id, @RequestBody Artwork artworkDetails) {
        return artworkRepository.findById(id).map(artwork -> {
            artwork.setTitle(artworkDetails.getTitle());
            artwork.setDescription(artworkDetails.getDescription());
            artwork.setPrice(artworkDetails.getPrice());
            artwork.setImageUrl(artworkDetails.getImageUrl());
            artwork.setCategory(artworkDetails.getCategory());
            artwork.setCulturalHistory(artworkDetails.getCulturalHistory());
            artwork.setApproved(artworkDetails.isApproved());
            artwork.setSold(artworkDetails.isSold());
            
            if (artworkDetails.getArtist() != null && artworkDetails.getArtist().getId() != null) {
                userRepository.findById(artworkDetails.getArtist().getId()).ifPresent(artwork::setArtist);
            }
            
            return ResponseEntity.ok(artworkRepository.save(artwork));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtwork(@PathVariable Long id) {
        return artworkRepository.findById(id).map(artwork -> {
            artworkRepository.delete(artwork);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
