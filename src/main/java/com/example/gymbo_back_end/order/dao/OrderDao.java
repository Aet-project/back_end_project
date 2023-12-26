package com.example.gymbo_back_end.order.dao;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.Order;

import java.util.List;

public interface OrderDao {

    Order save(Order order);

    Order findOne(Long orderSeq);

    List<Order> findOrdersByMember(Member member);

}
