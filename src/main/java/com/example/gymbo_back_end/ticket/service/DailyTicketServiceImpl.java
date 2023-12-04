package com.example.gymbo_back_end.ticket.service;

import com.example.gymbo_back_end.core.entity.DailyTicket;
import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.gym.dao.GymDao;
import com.example.gymbo_back_end.ticket.dto.DailyTicketRequestDto;
import com.example.gymbo_back_end.ticket.repository.DailyTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyTicketServiceImpl implements DailyTicketService{

    private final DailyTicketRepository dailyTicketRepository;
    private final GymDao gymDao;


    @Override
    public DailyTicket created(DailyTicketRequestDto dailyTicketRequestDto) {

        Gym gym = gymDao.findByGymNumber(dailyTicketRequestDto.getGymNumber()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 운동시설 입니다."));

        DailyTicket dailyTicket = DailyTicket.builder()
                .dailyTicketPrice(dailyTicketRequestDto.getDailyTicketPrice())
                .dailyTicketUse(dailyTicketRequestDto.getDailyTicketUse())
                .gym(gym)
                .build();
        dailyTicketRepository.save(dailyTicket);

        return dailyTicket;
    }


}
