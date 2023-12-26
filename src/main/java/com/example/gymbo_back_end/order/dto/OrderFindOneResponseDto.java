package com.example.gymbo_back_end.order.dto;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.Order;
import com.example.gymbo_back_end.core.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderFindOneResponseDto {


    private Long orderSeq;
    private Long memberSeq; //주문 회원

//    private List<OrderItem> orderItems = new ArrayList<>();

//    public void addOrderItem(OrderItem orderItem) {
//        orderItems.add(orderItem);
//    }
    public static OrderFindOneResponseDto createdOrderDto(Order order){
        OrderFindOneResponseDto orderFindOneResponseDto = new OrderFindOneResponseDto();
        orderFindOneResponseDto.setMemberSeq(order.getMember().getMemberSeq());
        orderFindOneResponseDto.setOrderSeq(order.getOrderSeq());

        return orderFindOneResponseDto;
    }

}
