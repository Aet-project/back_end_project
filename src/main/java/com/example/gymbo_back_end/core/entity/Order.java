package com.example.gymbo_back_end.core.entity;

import com.example.gymbo_back_end.order.code.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Table(name = "orders")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_seq")
    private Long orderSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private Member member; //주문 회원

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_created_at")
    private Date createdAt;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }



    //==비즈니스 로직==//
    public static Order createdOrder(Member member,  OrderItem orderItem){
        Order order = new Order();
        order.setMember(member);
        order.addOrderItem(orderItem);
        order.setOrderStatus(OrderStatus.ORDER);
        return order;
    }


    //==주문 취소 비즈니스 로직==//
    public void cancel() {
        this.setOrderStatus(OrderStatus.CANCEL);
    }
}
