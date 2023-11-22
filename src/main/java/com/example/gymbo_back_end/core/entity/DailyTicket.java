package com.example.gymbo_back_end.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "daily_ticket")
@Entity
public class DailyTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_ticket_seq")
    private Long dailyTicketSeq;        //일일권 시퀀스 넘버

    @Column(name = "daily_ticket_price")
    private String dailyTicketPrice;    //일일권 가격

    @Column(name = "daily_ticket_use")
    private Boolean dailyTicketUse;     //일일권 사용 가능 여부

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gym_created_at")
    protected Date createdAt;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "gym_seq")
    private Gym gym;



}

