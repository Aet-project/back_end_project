package com.example.gymbo_back_end.order.controller;

import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.DailyTicket;
import com.example.gymbo_back_end.order.dto.OrderRequestDto;
import com.example.gymbo_back_end.order.dto.OrderResponseDto;
import com.example.gymbo_back_end.order.service.OrderService;
import com.example.gymbo_back_end.ticket.dto.DailyTicketDto;
import com.example.gymbo_back_end.ticket.service.DailyTicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final DailyTicketService dailyTicketService;

    @PostMapping
    public ResponseEntity<ResBodyModel> orderSave(@RequestBody OrderRequestDto orderRequestDto) {

        List<DailyTicket> dailyTicketList = dailyTicketService.createdForOrder(orderRequestDto);

        List<DailyTicketDto> dailyTicketDtoList = new ArrayList<>();

        for (int i = 0; i < dailyTicketList.size(); i++) {
            DailyTicket dailyTicket = dailyTicketList.get(i);
            DailyTicketDto dailyTicketDto = DailyTicketDto.builder()
                    .dailyTicketPrice(dailyTicket.getDailyTicketPrice())
                    .dailyTicketUse(dailyTicket.getDailyTicketUse())
                    .ticketSeq(dailyTicket.getDailyTicketSeq())
                    .build();

            dailyTicketDtoList.add(dailyTicketDto);
        }

        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        for (DailyTicketDto dailyTicketDto : dailyTicketDtoList) {
            OrderResponseDto orderResponseDto = orderService.save(orderRequestDto, dailyTicketDto);
            orderResponseDtoList.add(orderResponseDto);
        }


        return AetResponse.toResponse(SuccessCode.SUCCESS,orderResponseDtoList);

    }
}
