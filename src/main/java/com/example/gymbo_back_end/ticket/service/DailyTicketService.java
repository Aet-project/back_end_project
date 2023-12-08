package com.example.gymbo_back_end.ticket.service;

import com.example.gymbo_back_end.core.entity.ticket.DailyTicket;

import com.example.gymbo_back_end.ticket.dto.DailyTicketRequestDto;

import java.util.List;

public interface DailyTicketService {

    DailyTicket createdForTest(DailyTicketRequestDto dailyTicketRequestDto);

    List<DailyTicket> createdForOrder(String gymName, String ticketPrice,int count);
}
