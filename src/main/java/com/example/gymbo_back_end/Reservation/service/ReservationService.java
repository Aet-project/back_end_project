package com.example.gymbo_back_end.Reservation.service;

import com.example.gymbo_back_end.core.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> findReservationByStartDay(String startDay);
}
