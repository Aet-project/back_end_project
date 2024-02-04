package com.example.gymbo_back_end.gym.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class GymPhotoVO {
    private String gymName;
    private String gymNumber;
    private List<MultipartFile> files;
}
