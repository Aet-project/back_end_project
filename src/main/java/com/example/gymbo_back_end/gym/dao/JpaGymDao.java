package com.example.gymbo_back_end.gym.dao;

import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.gym.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JpaGymDao implements GymDao{

    private final GymRepository gymRepository;

    @Override
    public Gym save(Gym gym) {
       return gymRepository.save(gym);
    }

    @Override
    public Optional<Gym> findByGymName(String gymName) {
        return gymRepository.findByGymName(gymName);
    }

    @Override
    public Optional<Gym> findByGymNumber(String gymNumber) {
        return gymRepository.findByGymNumber(gymNumber);
    }

    @Override
    public List<Gym> findAll() {

        List<Gym> gyms = gymRepository.findAll();
        return gyms;
    }
}
