package com.example.gymbo_back_end.ticket.service;

import com.example.gymbo_back_end.core.entity.DailyTicket;

import com.example.gymbo_back_end.ticket.dto.DailyTicketRequestDto;

public interface DailyTicketService {

    DailyTicket created(DailyTicketRequestDto dailyTicketRequestDto);
}
