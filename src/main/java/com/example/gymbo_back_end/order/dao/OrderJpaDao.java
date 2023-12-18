package com.example.gymbo_back_end.order.dao;

import com.example.gymbo_back_end.core.entity.Order;
import com.example.gymbo_back_end.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderJpaDao implements OrderDao {

    private final OrderRepository orderRepository;
    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
