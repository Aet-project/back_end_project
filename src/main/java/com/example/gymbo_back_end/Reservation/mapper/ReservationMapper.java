package com.example.gymbo_back_end.Reservation.mapper;

import com.example.gymbo_back_end.Reservation.dto.FindStartTimeResponseDto;
import com.example.gymbo_back_end.core.entity.Reservation;

import java.util.List;

public interface ReservationMapper {

    List<FindStartTimeResponseDto> toResponse(List<Reservation> reservationByStartDay);
}
