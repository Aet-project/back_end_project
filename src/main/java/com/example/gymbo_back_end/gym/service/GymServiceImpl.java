package com.example.gymbo_back_end.gym.service;

import com.example.gymbo_back_end.core.commom.response.Address;
import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.GymPhoto;
import com.example.gymbo_back_end.gym.dao.GymDao;
import com.example.gymbo_back_end.gym.dao.GymPhotoDao;
import com.example.gymbo_back_end.gym.dto.GymPhotoRequestDto;
import com.example.gymbo_back_end.gym.dto.GymSaveRequestDto;
import com.example.gymbo_back_end.gym.handler.GymFileHandler;
import com.example.gymbo_back_end.gym.repository.GymPhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GymServiceImpl implements GymService {

    private final GymDao gymDao;
    private final GymPhotoDao gymPhotoDao;
    private final GymFileHandler gymFileHandler;



    @Override
    public Gym save(GymSaveRequestDto gymSaveRequestDto) {

        Address address = new Address(gymSaveRequestDto.getCity()
                ,gymSaveRequestDto.getStreet()
                ,gymSaveRequestDto.getZipCode());


        Gym gym = Gym.builder()
                .gymName(gymSaveRequestDto.getGymName())
                .gymAddress(address)
                .gymNumber(gymSaveRequestDto.getGymNumber())
                .gymSports(gymSaveRequestDto.getGymSports())
                .managerNumber(gymSaveRequestDto.getManagerNumber())
                .build();

        return gymDao.save(gym);
    }

    @Override
    public Gym find(String gymName) {
        Gym findGym = gymDao.findByGymName(gymName);
        return findGym;
    }

    @Override
    public Gym find(Long gymSeq) {
       return gymDao.find(gymSeq);
    }

    @Override
    public List<Gym> findAll() {
        List<Gym> gyms = gymDao.findAll();
        return gyms;
    }

    @Override
    public Gym update(GymSaveRequestDto gymSaveRequestDto) {
        Gym gym = gymDao.findByGymNumber(gymSaveRequestDto.getGymNumber());
        gym.changeInfo(gymSaveRequestDto);

        return gym;
    }

}

