package com.example.gymbo_back_end.order.mapper;

import com.example.gymbo_back_end.core.entity.DailyTicket;
import com.example.gymbo_back_end.ticket.dto.DailyTicketDto;

public interface OrderMapper {
    DailyTicketDto toResponse(DailyTicket dailyTicket);
}
