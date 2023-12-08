package com.example.gymbo_back_end.ticket.service;

import com.example.gymbo_back_end.core.entity.ticket.DailyTicket;
import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.gym.dao.GymDao;
import com.example.gymbo_back_end.ticket.dao.TicketDao;
import com.example.gymbo_back_end.ticket.dto.DailyTicketRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyTicketServiceImpl implements DailyTicketService{

    private final TicketDao ticketDao;
    private final GymDao gymDao;


    @Override
    public DailyTicket createdForTest(DailyTicketRequestDto dailyTicketRequestDto) {

        Gym gym = gymDao.findByGymNumber(dailyTicketRequestDto.getGymNumber()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 운동시설 입니다."));

        DailyTicket dailyTicket = DailyTicket.builder()
                .dailyTicketPrice(dailyTicketRequestDto.getDailyTicketPrice())
                .dailyTicketUse(dailyTicketRequestDto.getDailyTicketUse())
                .gym(gym)
                .build();
      ticketDao.save(dailyTicket);

        return dailyTicket;
    }

    @Override
    public List<DailyTicket> createdForOrder(String gymName,String ticketPrice, int count) {


        List<DailyTicket> dailyTicketList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Gym gym = gymDao.findByGymName(gymName).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 운동시설 입니다."));


            DailyTicket dailyTicket = DailyTicket.builder()
                    .dailyTicketPrice(ticketPrice)
                    .dailyTicketUse(true)
                    .gym(gym)
                    .build();

            ticketDao.save(dailyTicket);
            dailyTicketList.add(dailyTicket);

        }

        return dailyTicketList;
    }


}
