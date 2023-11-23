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

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/gyms")
@RequiredArgsConstructor
public class GymController {


    public final GymService gymService;


    @PostMapping("/save")
    public ResponseEntity<ResBodyModel> gymRegistration(@RequestBody GymSaveRequestDto gymSaveRequestDto) {

        log.info("gymSaveRequestDto ={}",gymSaveRequestDto);
        log.info("gymSaveRequestDtoManNumber = {}",gymSaveRequestDto.getManagerNumber());

        Address address = new Address(gymSaveRequestDto.getCity()
                ,gymSaveRequestDto.getStreet()
                ,gymSaveRequestDto.getZipCode());


        Gym gym = Gym.builder()
                .gymName(gymSaveRequestDto.getGymName())
                .gymAddress(address)
                .gymNumber(gymSaveRequestDto.getGymNumber())
                .managerNumber(gymSaveRequestDto.getManagerNumber())
                .build();

        gymService.save(gym);
        return AetResponse.toResponse(SuccessCode.SUCCESS);

    }

    @GetMapping("/{gymName}")
    public ResponseEntity<ResBodyModel> findByGymName ( @PathVariable String gymName) {
        log.info("gymName = {}",gymName);

        GymResponseDto gymResponseDto = gymService.find(gymName);
        return AetResponse.toResponse(SuccessCode.SUCCESS,gymResponseDto);

    }

    @GetMapping("/all")
    public ResponseEntity<ResBodyModel> findAllGym () {
        List<GymResponseDto> gymResponseDtoList = gymService.findAll();
        return AetResponse.toResponse(SuccessCode.SUCCESS,gymResponseDtoList);
    }

    @PatchMapping
    private ResponseEntity<ResBodyModel> updateGym(@RequestBody GymSaveRequestDto gymSaveRequestDto) {
        GymResponseDto updateGymResponseDto = gymService.update(gymSaveRequestDto);
        return AetResponse.toResponse(SuccessCode.SUCCESS,updateGymResponseDto);

    }

}
