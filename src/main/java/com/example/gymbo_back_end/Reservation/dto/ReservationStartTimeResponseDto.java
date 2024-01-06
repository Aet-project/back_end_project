package com.example.gymbo_back_end.Reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReservationStartTimeResponseDto {

    private String gymName;
    private String startDay;
    private String startTime;

    public static  ReservationStartTimeResponseDto create(String gymName, String startDay, String startTime) {
        ReservationStartTimeResponseDto reservationStartTimeResponseDto = new ReservationStartTimeResponseDto();
        reservationStartTimeResponseDto.setStartTime(startTime);
        reservationStartTimeResponseDto.setStartDay(startDay);
        reservationStartTimeResponseDto.setGymName(gymName);
        return reservationStartTimeResponseDto;
    }
}
