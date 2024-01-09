package com.example.gymbo_back_end.gym.dao;

import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.gym.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
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
    public Gym findByGymName(String gymName) {
        return gymRepository.findByGymName(gymName).orElseThrow(()-> new EntityNotFoundException("존재하지 않는 운동시설 입니다."));
    }

    @Override
    public Gym findByGymNumber(String gymNumber) {
        return gymRepository.findByGymNumber(gymNumber).orElseThrow(()-> new EntityNotFoundException("존재하지 않는 운동시설 입니다."));
    }

    @Override
    public List<Gym> findAll() {
        List<Gym> gyms = gymRepository.findAll();
        return gyms;
    }

    @Override
    public Gym find(Long gymSeq) {
        Gym gym = gymRepository.findById(gymSeq).orElseThrow(() -> new EntityNotFoundException("운동시설을 찾을 수 없습니다."));
        return gym;
    }
}
