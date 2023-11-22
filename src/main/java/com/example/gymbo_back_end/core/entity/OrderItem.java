package com.example.gymbo_back_end.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_seq")
    private Long orderItemSeq;

    @ManyToOne
    @JoinColumn(name = "order_seq")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "daily_ticket_seq")
    private DailyTicket dailyTicket;

    private int count; //주문 수량


}
