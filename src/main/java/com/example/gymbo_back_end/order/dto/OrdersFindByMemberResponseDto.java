package com.example.gymbo_back_end.order.dto;

import com.example.gymbo_back_end.core.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrdersFindByMemberResponseDto {

    private Long orderSeq; //주문 번호
    private Date createdAt; //주문 날짜


}
