package com.klu.repository;

import com.klu.model.Order;
import com.klu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByVisitor(User visitor);
}
