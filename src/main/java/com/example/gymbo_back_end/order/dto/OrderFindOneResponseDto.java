package com.example.gymbo_back_end.order.dto;

import com.example.gymbo_back_end.core.entity.Order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFindOneResponseDto {


    private Long orderSeq;
    private Long memberSeq; //주문 회원


    public static OrderFindOneResponseDto createdOrderDto(Order order){
        OrderFindOneResponseDto orderFindOneResponseDto = new OrderFindOneResponseDto();
        orderFindOneResponseDto.setMemberSeq(order.getMember().getMemberSeq());
        orderFindOneResponseDto.setOrderSeq(order.getOrderSeq());

        return orderFindOneResponseDto;
    }

}
