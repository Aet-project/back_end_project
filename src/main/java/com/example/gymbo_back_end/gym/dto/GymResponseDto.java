package com.example.gymbo_back_end.gym.dto;

import com.example.gymbo_back_end.core.commom.response.Address;
import com.example.gymbo_back_end.core.entity.Gym;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GymResponseDto {

    private String gymName;

    private Address gymAddress;

    private String gymNumber;

    private String managerNumber;

    private String gymSports;


    @Builder
    public GymResponseDto(String gymName, Address gymAddress, String gymNumber, String managerNumber,String gymSports) {
        this.gymName = gymName;
        this.gymSports = gymSports;
        this.gymAddress = gymAddress;
        this.gymNumber = gymNumber;
        this.managerNumber = managerNumber;
    }

    public static GymResponseDto buildDto (Gym gym) {
       return GymResponseDto.builder()
               .gymAddress(gym.getGymAddress())
               .gymSports(gym.getGymSports())
               .gymNumber(gym.getGymNumber())
               .gymName(gym.getGymName())
               .managerNumber(gym.getManagerNumber())
               .build();
    }
}
