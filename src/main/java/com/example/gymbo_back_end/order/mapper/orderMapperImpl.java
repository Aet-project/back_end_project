package com.example.gymbo_back_end.order.mapper;

import com.example.gymbo_back_end.core.entity.*;
import com.example.gymbo_back_end.order.dto.FindByMemberResponseDto;
import com.example.gymbo_back_end.ticket.dto.DailyTicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class orderMapperImpl implements OrderMapper{

    /**DailyTicket 객체를  DailyTicketDto로 변환*/
    public DailyTicketDto toResponse(DailyTicket dailyTicket) {
      return  DailyTicketDto.builder()
              .dailyTicketPrice(dailyTicket.getDailyTicketPrice())
              .dailyTicketUse(dailyTicket.getDailyTicketUse())
              .ticketSeq(dailyTicket.getDailyTicketSeq())
              .build();
    }
    /**List<Order> orders를 List<FindByMemberResponseDto>로 변환*/
    public List<FindByMemberResponseDto> toResponse(List<Order> orders) {
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
        return ordersFindByMemberResponseDtoList;
    }
}
