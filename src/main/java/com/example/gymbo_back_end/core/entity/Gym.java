package com.example.gymbo_back_end.core.entity;


import com.example.gymbo_back_end.core.commom.response.Address;
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
@Table(name = "gym")
@Entity
public class Gym {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gym_seq")
    private Long gymSeq;

    @Column(name = "gym_name")
    private String gymName;

    @Column(name = "gym_address")
    @Embedded
    private Address gymAddress;

    @Column(name = "gym_number")
    private String gymNumber;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gym_created_at")
    protected Date createdAt;


}
