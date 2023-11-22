package com.example.gymbo_back_end.gym.repository;

import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym,Long> {

    Optional<Gym> findByGymName(String gymName);

    Optional<Gym> findByManagerNumber(String managerNumber);
}
