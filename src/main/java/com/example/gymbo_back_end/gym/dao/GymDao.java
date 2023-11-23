package com.example.gymbo_back_end.gym.dao;

import com.example.gymbo_back_end.core.entity.Gym;

import java.util.List;
import java.util.Optional;

public interface GymDao {

    Gym save(Gym gym);

    Optional<Gym> findByGymName(String gymName);

    Optional<Gym> findByGymNumber(String gymNumber);

    List<Gym> findAll();
}
