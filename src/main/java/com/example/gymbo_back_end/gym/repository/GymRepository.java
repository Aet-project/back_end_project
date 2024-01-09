package com.example.gymbo_back_end.gym.repository;

import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym,Long> {

    @Query()
    Optional<Gym> findByGymName(String gymName);

    Optional<Gym> findByGymNumber (String gymNumber);
}
