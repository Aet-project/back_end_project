package com.example.gymbo_back_end.OrderItem.service;

import com.example.gymbo_back_end.OrderItem.repository.OrderItemRepository;
import com.example.gymbo_back_end.core.entity.Order;
import com.example.gymbo_back_end.core.entity.OrderItem;
import com.example.gymbo_back_end.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepository orderItemRepository;

}
