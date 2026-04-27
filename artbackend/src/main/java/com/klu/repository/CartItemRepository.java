package com.klu.repository;

import com.klu.model.CartItem;
import com.klu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    void deleteByUser(User user);
    void deleteByUserAndArtwork_Id(User user, Long artworkId);
}
