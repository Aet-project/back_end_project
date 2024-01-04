package com.example.gymbo_back_end.Reservation.dao;

import com.example.gymbo_back_end.core.entity.Reservation;

import java.util.List;

public interface ReservationDao {

    List<Reservation> findReservationsByStartDay(String startDay);
}
