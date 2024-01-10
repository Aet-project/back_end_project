package com.example.gymbo_back_end.Reservation.dto;

import com.example.gymbo_back_end.core.entity.Reservation;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindStartTimeResponseDto {

    private String gymName;
    private String startDay;
    private String startTime;

    public static FindStartTimeResponseDto buildDto(Reservation reservation) {
        FindStartTimeResponseDto reservationStartTimeResponseDto = new FindStartTimeResponseDto();
        reservationStartTimeResponseDto.setStartTime(reservation.getStartTime());
        reservationStartTimeResponseDto.setStartDay(reservation.getStartDay());
        reservationStartTimeResponseDto.setGymName(reservation.getGym().getGymName());
        return reservationStartTimeResponseDto;
    }
}
