package com.example.gymbo_back_end.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_seq")
    private Long orderItemSeq;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_seq")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "daily_ticket_seq")
    private DailyTicket dailyTicket;

    private int orderPrice; //주문 가격

    private int count; //주문 수량


    //==생성 메서드==//
    public static OrderItem createOrderItem(DailyTicket dailyTicket, int count) {
        String dailyTicketPrice = dailyTicket.getDailyTicketPrice();
        int price = Integer.parseInt(dailyTicketPrice);
        int resultPrice = price * count; //티켓 가격과 수량을 곱함
        OrderItem orderItem = OrderItem.builder()
                .dailyTicket(dailyTicket)
                .count(count)
                .orderPrice(resultPrice) //주문 가격
                .build();
        return orderItem;
    }


}
