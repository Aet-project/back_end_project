package com.example.gymbo_back_end.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrdersFindByMemberResponseDto {

    private Long orderSeq; //주문 번호
    private Date createdAt; //주문 날짜

}
