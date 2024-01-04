package com.example.gymbo_back_end.order.repository;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findOrdersByMember(Member member);
}
