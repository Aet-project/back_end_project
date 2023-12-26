package com.example.gymbo_back_end.order.repository;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {


    List<Order> findOrdersByMember(Member member);
}
