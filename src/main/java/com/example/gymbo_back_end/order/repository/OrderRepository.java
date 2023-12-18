package com.example.gymbo_back_end.order.repository;

import com.example.gymbo_back_end.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
