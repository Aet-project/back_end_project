package com.example.gymbo_back_end.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_seq")
    private Long reservationSeq;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    public static Reservation createdReservation(String startTime, String endTime) {

        Reservation reservation = Reservation.builder()
                .startTime(startTime)
                .endTime(endTime).build();
        return reservation;

    }


}
