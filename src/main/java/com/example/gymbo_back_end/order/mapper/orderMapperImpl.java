package com.example.gymbo_back_end.order.mapper;

import com.example.gymbo_back_end.core.entity.DailyTicket;
import com.example.gymbo_back_end.ticket.dto.DailyTicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class orderMapperImpl implements OrderMapper{

    public DailyTicketDto toResponse(DailyTicket dailyTicket) {
      return  DailyTicketDto.builder()
              .dailyTicketPrice(dailyTicket.getDailyTicketPrice())
              .dailyTicketUse(dailyTicket.getDailyTicketUse())
              .ticketSeq(dailyTicket.getDailyTicketSeq())
              .build();
    }
}
