package com.example.gymbo_back_end.Reservation.repository;

import com.example.gymbo_back_end.core.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.startDay = :startDay")
    List<Reservation> findReservationsByStartDay(@Param("startDay") String startDay);

}
