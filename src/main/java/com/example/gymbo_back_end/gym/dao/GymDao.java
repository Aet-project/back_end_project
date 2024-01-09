package com.example.gymbo_back_end.gym.dao;

import com.example.gymbo_back_end.core.entity.Gym;

import java.util.List;
import java.util.Optional;

public interface GymDao {

    Gym save(Gym gym);

    Gym findByGymName(String gymName);

    Gym findByGymNumber(String gymNumber);

    List<Gym> findAll();

    Gym find(Long gymSeq);
}
