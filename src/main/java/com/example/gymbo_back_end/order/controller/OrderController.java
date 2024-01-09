package com.example.gymbo_back_end.order.controller;

import com.example.gymbo_back_end.OrderItem.service.OrderItemService;
import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.*;
import com.example.gymbo_back_end.order.dto.FindOneResponseDto;
import com.example.gymbo_back_end.order.dto.OrderRequestDto;
import com.example.gymbo_back_end.order.dto.OrderResponseDto;
import com.example.gymbo_back_end.order.dto.FindByMemberResponseDto;
import com.example.gymbo_back_end.order.service.OrderService;
import com.example.gymbo_back_end.ticket.dto.DailyTicketDto;
import com.example.gymbo_back_end.ticket.service.DailyTicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final DailyTicketService dailyTicketService;
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
        FindOneResponseDto findOneResponseDto = orderService.OrderFindMember(orderSeq);
        return AetResponse.toResponse(SuccessCode.SUCCESS,findOneResponseDto);
    }

    @GetMapping("/member/{memberSeq}") //회원번호로 주문 조회
    public ResponseEntity<ResBodyModel> memberFindOrder(@PathVariable Long memberSeq){
        List<Order> orders = orderService.memberFindOrders(memberSeq);

        List<FindByMemberResponseDto> ordersFindByMemberResponseDtoList = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = order.getOrderItems();

            for (OrderItem orderItem : orderItems) {
                DailyTicket dailyTicket = orderItem.getDailyTicket();
                Reservation reservation = dailyTicket.getReservation();
                Gym gym = dailyTicket.getGym();
                FindByMemberResponseDto responseDto = FindByMemberResponseDto.buildDto(order,orderItem,reservation,dailyTicket,gym);
                ordersFindByMemberResponseDtoList.add(responseDto);
            }
        }
        return AetResponse.toResponse(SuccessCode.SUCCESS,ordersFindByMemberResponseDtoList);
    }

    @GetMapping("/order_item/{orderSeq}") //주문 번호로 주문 상품 조회
    public ResponseEntity<ResBodyModel> orderItemFindOrder(@PathVariable Long orderSeq) {
        Order order = orderService.find(orderSeq);
        List<FindByMemberResponseDto> ordersFindByMemberResponseDtoList = new ArrayList<>();
        List<OrderItem> orderItemsByOrder = orderItemService.findOrderItemsByOrder(orderSeq);

        for (OrderItem orderItem : orderItemsByOrder) {
            DailyTicket dailyTicket = orderItem.getDailyTicket();
            Reservation reservation = dailyTicket.getReservation();
            Gym gym = dailyTicket.getGym();
            FindByMemberResponseDto responseDto = FindByMemberResponseDto.buildDto(order,orderItem,reservation,dailyTicket,gym);
            ordersFindByMemberResponseDtoList.add(responseDto);
        }
        return AetResponse.toResponse(SuccessCode.SUCCESS,ordersFindByMemberResponseDtoList);
    }



}
