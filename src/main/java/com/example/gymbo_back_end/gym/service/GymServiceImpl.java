package com.example.gymbo_back_end.gym.service;

import com.example.gymbo_back_end.core.commom.response.Address;
import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.GymPhoto;
import com.example.gymbo_back_end.gym.dao.GymDao;
import com.example.gymbo_back_end.gym.dto.GymPhotoRequestDto;
import com.example.gymbo_back_end.gym.dto.GymSaveRequestDto;
import com.example.gymbo_back_end.gym.handler.FileHandler;
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
    private final GymPhotoRepository gymPhotoRepository;
    private final FileHandler fileHandler;



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

    @Override
    @Transactional
    public List<GymPhoto> saveGymPhoto(GymPhotoRequestDto gymPhotoRequestDto, List<MultipartFile> files) throws Exception {

        Gym gym = gymDao.findByGymNumber(gymPhotoRequestDto.getGymNumber());
        List<GymPhoto> photoList = fileHandler.parseFileInfo(files);

        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()) {
            for(GymPhoto photo : photoList) {
                // 파일을 DB에 저장
                GymPhoto gymPhoto = gymPhotoRepository.save(photo);
                gym.addPhoto(gymPhoto);
            }
        }

        return photoList;
    }
    @Override
    @Transactional
    public List<GymPhoto> updateGymPhoto(GymPhotoRequestDto gymPhotoRequestDto, List<MultipartFile> addFileList) throws Exception{

        Gym gym = gymDao.findByGymNumber(gymPhotoRequestDto.getGymNumber());
        List<GymPhoto> photoList = fileHandler.parseFileInfo(addFileList);

        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()) {
            for(GymPhoto photo : photoList) {
                // 파일을 DB에 저장
                GymPhoto gymPhoto = gymPhotoRepository.save(photo);
                gym.addPhoto(gymPhoto);
            }
        }
        return photoList;
    }

    @Override
    public List<GymPhoto> findGymPhoto(String gymNumber) {
        Gym gym = gymDao.findByGymNumber(gymNumber);
        List<GymPhoto> gymPhotosByGym = gymPhotoRepository.findGymPhotosByGym(gym);
        return gymPhotosByGym;
    }


    @Override
    public GymPhoto findByGymPhotoSeq(Long gymPhotoSeq) {
       return gymPhotoRepository.findById(gymPhotoSeq).orElseThrow(() -> new EntityNotFoundException("파일이 존재하지 않습니다."));
    }
}

