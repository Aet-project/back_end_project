package com.example.gymbo_back_end.ticket.repository;

import com.example.gymbo_back_end.core.entity.DailyTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyTicketRepository extends JpaRepository<DailyTicket, Long> {
}
