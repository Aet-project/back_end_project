package com.example.gymbo_back_end.ticket.dao;

import com.example.gymbo_back_end.core.entity.DailyTicket;
import com.example.gymbo_back_end.core.entity.Gym;

import java.util.List;

public interface TicketDao {

    DailyTicket save(DailyTicket dailyTicket);

    DailyTicket find(Long id);

    List<DailyTicket> findDailyTicketsByGym(Gym gym);
}
