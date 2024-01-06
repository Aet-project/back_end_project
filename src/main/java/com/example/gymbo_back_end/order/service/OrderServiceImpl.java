package com.example.gymbo_back_end.order.service;

import com.example.gymbo_back_end.core.entity.*;
import com.example.gymbo_back_end.core.entity.DailyTicket;
import com.example.gymbo_back_end.member.dao.MemberDao;
import com.example.gymbo_back_end.order.dao.OrderDao;
import com.example.gymbo_back_end.order.dto.OrderFindOneResponseDto;
import com.example.gymbo_back_end.order.dto.OrderRequestDto;
import com.example.gymbo_back_end.order.dto.OrderResponseDto;
import com.example.gymbo_back_end.ticket.dao.TicketDao;
import com.example.gymbo_back_end.ticket.dto.DailyTicketDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService{

    private final TicketDao ticketDao;
    private final MemberDao memberDao;
    private final OrderDao orderDao;


    @Override
    public OrderResponseDto save(OrderRequestDto orderRequestDto, DailyTicketDto dailyTicketDto) {
        String memberId = orderRequestDto.getMemberId();
        Member member = memberDao.findByMemberId(memberId).orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));
        DailyTicket ticket = ticketDao.find(dailyTicketDto.getTicketSeq());
        OrderItem orderItem = OrderItem.createOrderItem(ticket, orderRequestDto.getOrderCount());


        Order order = Order.createdOrder(member,orderItem);
        Order saveOrder = orderDao.save(order);

        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .orderSeq(saveOrder.getOrderSeq())
                .memberSeq(saveOrder.getMember().getMemberSeq())
                .memberId(saveOrder.getMember().getMemberId())
                .nickName(saveOrder.getMember().getNickName())
                .build();

        return orderResponseDto;
    }

    @Override
    public OrderFindOneResponseDto OrderFindMember(Long orderSeq) {
        Order order = orderDao.findOne(orderSeq);
        OrderFindOneResponseDto orderFindOneResponseDto = OrderFindOneResponseDto.createdOrderDto(order);

        return orderFindOneResponseDto;
    }

    @Override
    public List<Order> memberFindOrders(Long memberSeq) {
        Member member = memberDao.find(memberSeq);
        List<Order> ordersByMember = orderDao.findOrdersByMember(member);
        return ordersByMember;
    }


}
