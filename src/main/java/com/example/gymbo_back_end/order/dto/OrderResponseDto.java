package com.example.gymbo_back_end.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDto {

    private Long orderSeq;

    private Long memberSeq;
    private String memberId;
    private String nickName;

    private String startTime; // 예약 시작 시간
    private String endTime; // 예약 종료 시간


}
