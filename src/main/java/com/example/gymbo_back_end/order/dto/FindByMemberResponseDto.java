package com.example.gymbo_back_end.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FindByMemberResponseDto {

    private Long orderSeq; //주문 번호
    private Long gymSeq; //주문 체육관 번호
    private Long dailyTicketSeq; // 티켓 번호
    private Long orderItemSeq; // 주문 상품 번호

    private String gymName; // 주문 체육관 이름
    private int count; //주문 수량
    private String reservationTime; //예약시간
    private Boolean dailyTicketUse; // 티켓 사용 여부
    private Date createdAt; //주문 날짜
    public static FindByMemberResponseDto creat(Long orderSeq, Date createdAt, String  gymName, int count
            ,String reservationTime, Long gymSeq, Long orderItemSeq
            ,Long dailyTicketSeq, Boolean dailyTicketUse) {

        FindByMemberResponseDto responseDto = new FindByMemberResponseDto();
        responseDto.setCreatedAt(createdAt);
        responseDto.setOrderSeq(orderSeq);
        responseDto.setGymName(gymName);
        responseDto.setGymSeq(gymSeq);
        responseDto.setCount(count);
        responseDto.setReservationTime(reservationTime);
        responseDto.setDailyTicketSeq(dailyTicketSeq);
        responseDto.setDailyTicketUse(dailyTicketUse);
        responseDto.setOrderItemSeq(orderItemSeq);

        return responseDto;
    }
//    private List<OrderItem> orderItems;


}
