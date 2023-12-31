package com.example.gymbo_back_end.gym.service;

import com.example.gymbo_back_end.core.commom.response.Address;
import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.gym.controller.GymController;
import com.example.gymbo_back_end.gym.dao.GymDao;
import com.example.gymbo_back_end.gym.dto.GymResponseDto;
import com.example.gymbo_back_end.gym.dto.GymSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GymServiceImpl implements GymService {

    private final GymDao gymDao;

    @Override
    public Gym save(Gym gym) {
        return gymDao.save(gym);
    }

    @Override
    public GymResponseDto find(String gymName) {

        Gym findGym = gymDao.findByGymName(gymName).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 운동시설 입니다."));
        GymResponseDto gymResponseDto = GymResponseDto.buildDto(findGym);
        return gymResponseDto;
    }

    @Override
    public List<GymResponseDto> findAll() {

        List<GymResponseDto> gymResponseDtoList = new ArrayList<>();
        List<Gym> gyms = gymDao.findAll();

        for (Gym gym : gyms) {
            GymResponseDto gymResponseDto = GymResponseDto.buildDto(gym);
            gymResponseDtoList.add(gymResponseDto);
        }
        return gymResponseDtoList;
    }

    @Override
    public GymResponseDto update(GymSaveRequestDto gymSaveRequestDto) {
        Gym gym = gymDao.findByGymNumber(gymSaveRequestDto.getGymNumber()).orElseThrow(() -> new EntityNotFoundException("등록된 운동시설이 없습ㄴ디ㅏ."));
        gym.changeInfo(gymSaveRequestDto);

        GymResponseDto gymResponseDto = GymResponseDto.buildDto(gym);

        return gymResponseDto;
    }
}
