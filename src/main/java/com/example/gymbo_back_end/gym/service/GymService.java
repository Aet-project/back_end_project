package com.example.gymbo_back_end.gym.service;

import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.gym.controller.GymController;
import com.example.gymbo_back_end.gym.dto.GymResponseDto;
import com.example.gymbo_back_end.gym.dto.GymSaveRequestDto;

import java.util.List;

public interface GymService {

    Gym save(GymSaveRequestDto gymSaveRequestDto);

    GymResponseDto find(String gymName);

    Gym find(Long gymSeq);

    List<Gym> findAll();

    GymResponseDto update(GymSaveRequestDto gymSaveRequestDto);
}
