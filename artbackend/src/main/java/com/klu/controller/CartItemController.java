package com.klu.controller;

import com.klu.model.CartItem;
import com.klu.model.User;
import com.klu.repository.CartItemRepository;
import com.klu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartItemController {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public List<CartItem> getCartItems(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return cartItemRepository.findByUser(user);
        }
        return null;
    }

    @PostMapping
    public CartItem addToCart(@RequestBody CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @DeleteMapping("/{userId}/{artworkId}")
    @Transactional
    public void removeFromCart(@PathVariable Long userId, @PathVariable Long artworkId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            cartItemRepository.deleteByUserAndArtwork_Id(user, artworkId);
        }
    }

    @DeleteMapping("/{userId}")
    @Transactional
    public void clearCart(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            cartItemRepository.deleteByUser(user);
        }
    }
}
