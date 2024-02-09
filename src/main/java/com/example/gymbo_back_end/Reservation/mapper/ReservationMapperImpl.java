package com.example.gymbo_back_end.Reservation.mapper;

import com.example.gymbo_back_end.Reservation.dto.FindStartTimeResponseDto;
import com.example.gymbo_back_end.core.entity.Reservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public List<FindStartTimeResponseDto> toResponse(List<Reservation> reservationByStartDay){
        List<FindStartTimeResponseDto> responseDtoList = new ArrayList<>();

        for (Reservation reservation : reservationByStartDay) {
            FindStartTimeResponseDto responseDto = FindStartTimeResponseDto.buildDto(reservation);
            responseDtoList.add(responseDto);
        }

        return responseDtoList;

    }

}
