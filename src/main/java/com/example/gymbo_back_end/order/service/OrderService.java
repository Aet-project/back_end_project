package com.example.gymbo_back_end.order.service;

import com.example.gymbo_back_end.order.dto.OrderRequestDto;
import com.example.gymbo_back_end.order.dto.OrderResponseDto;
import com.example.gymbo_back_end.ticket.dto.DailyTicketDto;

public interface OrderService {


    OrderResponseDto save(OrderRequestDto orderRequestDto, DailyTicketDto dailyTicketDto);

}
