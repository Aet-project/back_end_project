package com.example.gymbo_back_end.order.controller;

import com.example.gymbo_back_end.OrderItem.repository.OrderItemRepository;
import com.example.gymbo_back_end.OrderItem.service.OrderItemService;
import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.DailyTicket;
import com.example.gymbo_back_end.core.entity.Order;
import com.example.gymbo_back_end.core.entity.OrderItem;
import com.example.gymbo_back_end.order.dto.OrderFindOneResponseDto;
import com.example.gymbo_back_end.order.dto.OrderRequestDto;
import com.example.gymbo_back_end.order.dto.OrderResponseDto;
import com.example.gymbo_back_end.order.dto.OrdersFindByMemberResponseDto;
import com.example.gymbo_back_end.order.service.OrderService;
import com.example.gymbo_back_end.ticket.dto.DailyTicketDto;
import com.example.gymbo_back_end.ticket.service.DailyTicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final DailyTicketService dailyTicketService;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<ResBodyModel> orderSave(@RequestBody OrderRequestDto orderRequestDto) {

        DailyTicket dailyTicket = dailyTicketService.createdForOrder(orderRequestDto);//티켓을 생성


            DailyTicketDto dailyTicketDto = DailyTicketDto.builder()
                    .dailyTicketPrice(dailyTicket.getDailyTicketPrice())
                    .dailyTicketUse(dailyTicket.getDailyTicketUse())
                    .ticketSeq(dailyTicket.getDailyTicketSeq())
                    .build();

        OrderResponseDto orderResponseDto = orderService.save(orderRequestDto, dailyTicketDto);


        return AetResponse.toResponse(SuccessCode.SUCCESS,orderResponseDto);

    }
    @GetMapping("/{orderSeq}") //주문번호로 주문한 회원 조회
    public ResponseEntity<ResBodyModel> orderFindMember(@PathVariable Long orderSeq) {
        OrderFindOneResponseDto findOneResponseDto = orderService.OrderFindMember(orderSeq);
        return AetResponse.toResponse(SuccessCode.SUCCESS,findOneResponseDto);
    }

    @GetMapping("/member/{memberSeq}") //회원번호로 주문 조회
    public ResponseEntity<ResBodyModel> memberFindOrder(@PathVariable Long memberSeq){
        List<Order> orders = orderService.memberFindOrders(memberSeq);

        List<OrdersFindByMemberResponseDto> ordersFindByMemberResponseDtoList = new ArrayList<>();
        for (Order order : orders) {
            OrdersFindByMemberResponseDto ordersFindByMemberResponseDto = new OrdersFindByMemberResponseDto();
            ordersFindByMemberResponseDto.setOrderSeq(order.getOrderSeq());
            ordersFindByMemberResponseDto.setCreatedAt(order.getCreatedAt());
            ordersFindByMemberResponseDto.setOrderItems(order.getOrderItems());
            ordersFindByMemberResponseDtoList.add(ordersFindByMemberResponseDto);
        }
        return AetResponse.toResponse(SuccessCode.SUCCESS,ordersFindByMemberResponseDtoList);
    }

    @GetMapping("/order_item/{orderSeq}") //주문 번호로 주문 상품 조회
    public ResponseEntity<ResBodyModel> orderItemFindOrder(@PathVariable Long orderSeq) {
        List<OrderItem> orderItemsByOrder = orderItemService.findOrderItemsByOrder(orderSeq);
        return AetResponse.toResponse(SuccessCode.SUCCESS,orderItemsByOrder);
    }



}
