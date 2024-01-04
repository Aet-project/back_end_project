package com.example.gymbo_back_end.OrderItem.repository;

import com.example.gymbo_back_end.core.entity.Order;
import com.example.gymbo_back_end.core.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    List<OrderItem> findOrderItemsByOrder(Order order);

 }
