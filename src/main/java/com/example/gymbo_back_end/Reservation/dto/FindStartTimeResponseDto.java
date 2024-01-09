package com.example.gymbo_back_end.Reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindStartTimeResponseDto {

    private String gymName;
    private String startDay;
    private String startTime;

    public static FindStartTimeResponseDto create(String gymName, String startDay, String startTime) {
        FindStartTimeResponseDto reservationStartTimeResponseDto = new FindStartTimeResponseDto();
        reservationStartTimeResponseDto.setStartTime(startTime);
        reservationStartTimeResponseDto.setStartDay(startDay);
        reservationStartTimeResponseDto.setGymName(gymName);
        return reservationStartTimeResponseDto;
    }
}
