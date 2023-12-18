package com.example.gymbo_back_end.ticket.service;

import com.example.gymbo_back_end.core.entity.DailyTicket;

import com.example.gymbo_back_end.order.dto.OrderRequestDto;
import com.example.gymbo_back_end.ticket.dto.DailyTicketRequestDto;

import java.util.List;

public interface DailyTicketService {

    DailyTicket createdForTest(DailyTicketRequestDto dailyTicketRequestDto);

    List<DailyTicket> createdForOrder(OrderRequestDto orderRequestDto);
}
