package com.example.gymbo_back_end.gym.service;

import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.GymPhoto;
import com.example.gymbo_back_end.gym.dto.GymPhotoRequestDto;
import com.example.gymbo_back_end.gym.dto.GymSaveRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GymService {

    Gym save(GymSaveRequestDto gymSaveRequestDto);

    Gym find(String gymName);

    Gym find(Long gymSeq);

    List<Gym> findAll();

    Gym update(GymSaveRequestDto gymSaveRequestDto);

    Gym findByGymNumber(String gymNumber);

}
