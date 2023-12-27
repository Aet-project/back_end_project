package com.example.gymbo_back_end.order.service;

import com.example.gymbo_back_end.core.entity.*;
import com.example.gymbo_back_end.core.entity.DailyTicket;
import com.example.gymbo_back_end.gym.dao.GymDao;
import com.example.gymbo_back_end.member.dao.MemberDao;
import com.example.gymbo_back_end.order.dao.OrderDao;
import com.example.gymbo_back_end.order.dto.OrderFindOneResponseDto;
import com.example.gymbo_back_end.order.dto.OrderRequestDto;
import com.example.gymbo_back_end.order.dto.OrderResponseDto;
import com.example.gymbo_back_end.order.repository.OrderRepository;
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
    private final OrderRepository orderRepository;



    @Override
    public OrderResponseDto save(OrderRequestDto orderRequestDto, List<DailyTicketDto> dailyTicketList) {
        String memberId = orderRequestDto.getMemberId();
        Member member = memberDao.findByMemberId(memberId).orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));


        List<OrderItem> orderItems = new ArrayList<>();

        for (DailyTicketDto dailyTicket : dailyTicketList) {
            DailyTicket ticket1 = ticketDao.find(dailyTicket.getTicketSeq());
            OrderItem orderItem = OrderItem.createOrderItem(ticket1, orderRequestDto.getOrderCount());
            orderItems.add(orderItem);
        }




        Order order = Order.createdOrder(member,orderItems);
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
