package com.example.gymbo_back_end.Reservation.controller;

import com.example.gymbo_back_end.Reservation.dto.ReservationFindStartDayRequestDto;
import com.example.gymbo_back_end.Reservation.service.ReservationService;
import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.Reservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    @PostMapping("/start_day") // 시작 날짜로 예약 조회
    public ResponseEntity<ResBodyModel> findReservationByStartDay(@RequestBody ReservationFindStartDayRequestDto reservationFindStartDayRequestDto){
        String startDay = reservationFindStartDayRequestDto.getStartDay();
        List<Reservation> reservationByStartDay = reservationService.findReservationByStartDay(startDay);
        return AetResponse.toResponse(SuccessCode.SUCCESS,reservationByStartDay);
    }
}
