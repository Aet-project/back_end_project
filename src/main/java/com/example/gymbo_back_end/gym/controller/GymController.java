package com.example.gymbo_back_end.gym.controller;

import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.Address;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.gym.dto.GymResponseDto;
import com.example.gymbo_back_end.gym.dto.GymSaveRequestDto;
import com.example.gymbo_back_end.gym.service.GymService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/gyms")
@RequiredArgsConstructor
public class GymController {


    public final GymService gymService;


    @PostMapping("/save") //운동 시설 등록
    public ResponseEntity<ResBodyModel> gymRegistration(@RequestBody GymSaveRequestDto gymSaveRequestDto) {

        log.info("gymSaveRequestDto ={}",gymSaveRequestDto);
        log.info("gymSaveRequestDtoManNumber = {}",gymSaveRequestDto.getManagerNumber());


        Gym gym = gymService.save(gymSaveRequestDto);
        GymResponseDto gymResponseDto = GymResponseDto.buildDto(gym);
        return AetResponse.toResponse(SuccessCode.SUCCESS,gymResponseDto);

    }

    @GetMapping("/{gymName}") //운동시설 이름으로 조회
    public ResponseEntity<ResBodyModel> findByGymName ( @PathVariable String gymName) {

        log.info("gymName = {}",gymName);

        GymResponseDto gymResponseDto = gymService.find(gymName);
        return AetResponse.toResponse(SuccessCode.SUCCESS,gymResponseDto);

    }

    @GetMapping("/all") // 운동시설 전체 조회
    public ResponseEntity<ResBodyModel> findAllGym () {

        List<Gym> gyms = gymService.findAll();
        List<GymResponseDto> gymResponseDtoList = new ArrayList<>();
        for (Gym gym : gyms) {
            GymResponseDto gymResponseDto = GymResponseDto.buildDto(gym);
            gymResponseDtoList.add(gymResponseDto);
        }

        return AetResponse.toResponse(SuccessCode.SUCCESS,gymResponseDtoList);
    }

    @PatchMapping //운동시설 업데이트
    private ResponseEntity<ResBodyModel> updateGym(@RequestBody GymSaveRequestDto gymSaveRequestDto) {
        GymResponseDto updateGymResponseDto = gymService.update(gymSaveRequestDto);
        return AetResponse.toResponse(SuccessCode.SUCCESS,updateGymResponseDto);

    }

}
