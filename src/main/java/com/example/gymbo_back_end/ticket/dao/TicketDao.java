package com.example.gymbo_back_end.ticket.dao;

import com.example.gymbo_back_end.core.entity.DailyTicket;

public interface TicketDao {

    DailyTicket save(DailyTicket dailyTicket);

    DailyTicket find(Long id);
}
